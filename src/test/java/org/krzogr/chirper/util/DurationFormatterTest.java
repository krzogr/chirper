package org.krzogr.chirper.util;

import java.time.LocalDateTime;

import org.junit.Test;

import static java.time.Duration.between;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.junit.Assert.*;
import static org.krzogr.chirper.util.TestUtils.assertUtilityClassWellDefined;

/** Unit test to verify DurationFormatter. */
public class DurationFormatterTest {
  @Test
  public void testDurationFormatter() {
    assertFormattingAllUnits("2014-01-01 11:11:11", "2014-01-01 11:11:11", "0 seconds");

    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-01 11:11:11", "1 minute");

    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-01 12:11:11", "1 hour, 1 minute");

    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-01 15:16:51", "4 hours, 6 minutes, 40 seconds");

    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-01 11:11:12", "1 minute, 1 second");

    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-02 11:10:11", "1 day");

    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-02 12:10:11", "1 day, 1 hour");

    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-02 11:10:12", "1 day, 1 second");

    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-02 12:11:11", "1 day, 1 hour, 1 minute");

    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-12 11:10:11", "11 days");

    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-13 23:15:11", "12 days, 12 hours, 5 minutes");

    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-14 11:10:34", "13 days, 23 seconds");

    assertFormattingAllUnits("2014-01-01 11:10:11", "2014-01-15 17:43:04", "14 days, 6 hours, 32 minutes, 53 seconds");

    assertFormattingHighestUnit("2014-01-01 11:11:11", "2014-01-01 11:11:11", "0 seconds");

    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-01 11:11:11", "1 minute");

    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-01 12:11:11", "1 hour");

    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-01 15:16:51", "4 hours");

    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-01 11:11:12", "1 minute");

    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-02 11:10:11", "1 day");

    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-02 12:10:11", "1 day");

    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-02 11:10:12", "1 day");

    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-02 12:11:11", "1 day");
    
    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-12 11:10:11", "11 days");

    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-13 23:15:11", "12 days");

    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-14 11:10:34", "13 days");

    assertFormattingHighestUnit("2014-01-01 11:10:11", "2014-01-15 17:43:04", "14 days");

    assertUtilityClassWellDefined(DurationFormatter.class);
  }

  private void assertFormattingAllUnits(String startDateTime, String endDateTime, String expectedDiff) {
    assertFormatting(startDateTime, endDateTime, expectedDiff, false);
  }

  private void assertFormattingHighestUnit(String startDateTime, String endDateTime, String expectedDiff) {
    assertFormatting(startDateTime, endDateTime, expectedDiff, true);
  }

  private void assertFormatting(String startDateTime, String endDateTime, String expectedDiff, boolean highestUnitOnly) {
    LocalDateTime start = LocalDateTime.parse(startDateTime, ofPattern("yyyy-MM-dd HH:mm:ss"));
    LocalDateTime end = LocalDateTime.parse(endDateTime, ofPattern("yyyy-MM-dd HH:mm:ss"));

    String actualDiff = DurationFormatter.format(between(start, end), highestUnitOnly);

    assertEquals(expectedDiff, actualDiff);
  }
}
