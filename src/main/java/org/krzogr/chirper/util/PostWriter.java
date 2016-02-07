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

import java.io.PrintStream;
import java.time.LocalDateTime;

import org.krzogr.chirper.service.Post;

/** Utility class to facilitate writing posts to output streams. */
public final class PostWriter {
  private final PrintStream output;
  private final LocalDateTime referenceTime;
  private final PostFormatter formatter;

  public PostWriter(final PrintStream output, final LocalDateTime referenceTime, final PostFormatter formatter) {
    this.output = output;
    this.referenceTime = referenceTime;
    this.formatter = formatter;
  }

  public void write(final Post post) {
    output.println(formatter.format(post, referenceTime));
  }
}
