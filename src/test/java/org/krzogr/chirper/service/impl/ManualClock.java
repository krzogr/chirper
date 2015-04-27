package org.krzogr.chirper.service.impl;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Special implementation of Clock which enables to manually change current
 * date/time.
 */
public class ManualClock extends Clock {
  private final ZoneId zone;
  private Instant instant;

  public ManualClock() {
    this.zone = ZoneId.systemDefault();
    this.instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
  }

  @Override
  public ZoneId getZone() {
    return zone;
  }

  @Override
  public Clock withZone(ZoneId zone) {
    return this;
  }

  @Override
  public long millis() {
    return instant.toEpochMilli();
  }

  @Override
  public Instant instant() {
    return instant;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ManualClock) {
      ManualClock other = (ManualClock) obj;
      return instant.equals(other.instant) && zone.equals(other.zone);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return instant.hashCode() ^ zone.hashCode();
  }

  @Override
  public String toString() {
    return "ManualClock[" + instant + "," + zone + "]";
  }

  public void setDateTime(String date, String time) {
    LocalDateTime newDateTime = LocalDateTime.parse(date + " " + time,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    instant = newDateTime.toInstant(ZoneOffset.UTC);
  }

  public void setTime(String time) {
    LocalDateTime curDateTime = LocalDateTime.ofInstant(instant, zone);
    LocalTime curTime = curDateTime.toLocalTime();
    LocalTime newTime = LocalTime.parse(time,
        DateTimeFormatter.ofPattern("HH:mm:ss"));

    instant = curDateTime.plus(Duration.between(curTime, newTime)).toInstant(
        ZoneOffset.UTC);
  }
}
