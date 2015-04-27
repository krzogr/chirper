package org.krzogr.chirper.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test to verify DurationFormatter.
 */
public class DurationFormatterTest {
  @Test
  public void testDurationFormatter() {
    assertFormattingAllUnits("2014-01-01 11:11:11", "2014-01-01 11:11:11",
        "0 seconds");
    
    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-01 11:11:11",
        "1 minute");
    
    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-01 12:11:11",
        "1 hour, 1 minute");
    
    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-01 15:16:51",
        "4 hours, 6 minutes, 40 seconds");
    
    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-01 11:11:12",
        "1 minute, 1 second");
    
    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-02 11:10:11",
        "1 day");
    
    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-02 12:10:11",
        "1 day, 1 hour");
    
    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-02 11:10:12",
        "1 day, 1 second");
    
    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-02 12:11:11",
        "1 day, 1 hour, 1 minute");
    
    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-12 11:10:11",
        "11 days");
    
    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-13 23:15:11",
        "12 days, 12 hours, 5 minutes");
    
    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-14 11:10:34",
        "13 days, 23 seconds");
    
    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-15 17:43:04",
        "14 days, 6 hours, 32 minutes, 53 seconds");

    assertFormattingHighestUnit("2014-01-01 11:11:11", "2014-01-01 11:11:11",
        "0 seconds");
    
    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-01 11:11:11",
        "1 minute");
    
    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-01 12:11:11",
        "1 hour");
    
    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-01 15:16:51",
        "4 hours");
    
    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-01 11:11:12",
        "1 minute");
    
    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-02 11:10:11",
        "1 day");
    
    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-02 12:10:11",
        "1 day");
    
    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-02 11:10:12",
        "1 day");
    
    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-02 12:11:11",
        "1 day");
    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-12 11:10:11",
        "11 days");
    
    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-13 23:15:11",
        "12 days");
    
    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-14 11:10:34",
        "13 days");
    
    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-15 17:43:04",
        "14 days");

    TestUtils.assertUtilityClassWellDefined(DurationFormatter.class);
  }

  private void assertFormattingAllUnits(String startDateTimeStr,
      String endDateTimeStr, String expected) {
    assertFormatting(startDateTimeStr, endDateTimeStr, expected, false);
  }

  private void assertFormattingHighestUnit(String startDateTimeStr,
      String endDateTimeStr, String expected) {
    assertFormatting(startDateTimeStr, endDateTimeStr, expected, true);
  }

  private void assertFormatting(String startDateTimeStr, String endDateTimeStr,
      String expected, boolean highestUnitOnly) {
    LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeStr,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeStr,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    String actual = DurationFormatter.format(
        Duration.between(startDateTime, endDateTime), highestUnitOnly);
    assertEquals(expected, actual);
  }
}
