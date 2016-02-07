/*
 * Copyright (C) 2015 krzogr (krzogr@gmail.com)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.krzogr.chirper.util;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/** Utility class to format time duration to a user friendly string. */
public final class DurationFormatter {
  private DurationFormatter() {
  }

  /** Formats the specified time duration to a user friendly string.
   * 
   * @param duration Time duration to format.
   * @param highestUnitOnly Use only the highest time unit which the duration
   *        could be described with.
   * @return User friendly representation of time duration. */
  public static String format(final Duration duration, final boolean highestUnitOnly) {
    StringBuilder result = new StringBuilder();
    String sep = "";

    long days = duration.toDays();
    if (days > 0) {
      result.append(days).append(days == 1 ? " day" : " days");
      sep = ", ";

      if (highestUnitOnly) {
        return result.toString();
      }
    }

    long hours = duration.minusDays(days).toHours();
    if (hours > 0) {
      result.append(sep).append(hours).append(hours == 1 ? " hour" : " hours");
      sep = ", ";

      if (highestUnitOnly) {
        return result.toString();
      }
    }

    long minutes = duration.minusDays(days).minusHours(hours).toMinutes();
    if (minutes > 0) {
      result.append(sep).append(minutes).append(minutes == 1 ? " minute" : " minutes");
      sep = ", ";

      if (highestUnitOnly) {
        return result.toString();
      }
    }

    long seconds = TimeUnit.MILLISECONDS.toSeconds(duration.minusDays(days).minusHours(hours).minusMinutes(minutes)
                                                           .toMillis());
    if (seconds > 0 || result.length() == 0) {
      result.append(sep).append(seconds).append(seconds == 1 ? " second" : " seconds");
    }

    return result.toString();
  }
}
