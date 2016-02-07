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

package org.krzogr.chirper.command.impl;

import java.io.PrintStream;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Objects;

import org.krzogr.chirper.service.UserManager;
import org.krzogr.chirper.util.PostFormatter;
import org.krzogr.chirper.util.PostWriter;

/** Represents the command which displays user timeline posts.
 * <p>
 * Timeline posts are the posts which were sent by the user.
 * </p> */
public final class DisplayTimelineCommand implements Runnable {
  private final UserManager userManager;
  private final String userName;
  private final PrintStream output;
  private final Clock clock;

  public DisplayTimelineCommand(final UserManager userManager, final String userName, final PrintStream output,
                                final Clock clock) {
    Objects.requireNonNull(userManager);
    Objects.requireNonNull(userName);
    Objects.requireNonNull(output);
    Objects.requireNonNull(clock);

    this.userManager = userManager;
    this.userName = userName;
    this.output = output;
    this.clock = clock;
  }

  @Override
  public void run() {
    PostWriter writer = new PostWriter(output, LocalDateTime.now(clock), new PostFormatter(false, true));

    userManager.getOrCreateUser(userName).getTimeline().forEach(p -> writer.write(p));
  }
}