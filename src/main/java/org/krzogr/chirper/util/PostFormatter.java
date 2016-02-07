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

import static java.time.Duration.between;

import java.time.LocalDateTime;

import org.krzogr.chirper.service.Post;

/** Utility class to format Post to a user friendly string. */
public final class PostFormatter {
  private final boolean includeUserName;
  private final boolean highestDurationUnitOnly;

  public PostFormatter(final boolean includeUserName, final boolean highestDurationUnitOnly) {
    this.includeUserName = includeUserName;
    this.highestDurationUnitOnly = highestDurationUnitOnly;
  }

  public String format(final Post post, final LocalDateTime referenceTime) {
    StringBuilder buffer = new StringBuilder();

    if (includeUserName) {
      buffer.append(post.getUser().getUserName()).append(" - ");
    }

    buffer.append(post.getText());
    buffer.append(" (");
    buffer.append(formatPostAge(post, referenceTime));
    buffer.append(" ago)");

    return buffer.toString();
  }

  private String formatPostAge(final Post post, final LocalDateTime referenceTime) {
    return DurationFormatter.format(between(post.getCreationTime(), referenceTime), highestDurationUnitOnly);
  }
}
