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
import java.util.Objects;

import org.krzogr.chirper.command.CommandFactory;
import org.krzogr.chirper.service.UserManager;

/** Default implementation of command factory. */
public final class DefaultCommandFactory implements CommandFactory {
  private final UserManager userManager;
  private final PrintStream output;
  private final Clock clock;

  public DefaultCommandFactory(final UserManager userManager, final PrintStream output, final Clock clock) {
    Objects.requireNonNull(userManager);
    Objects.requireNonNull(output);
    Objects.requireNonNull(clock);

    this.userManager = userManager;
    this.output = output;
    this.clock = clock;
  }

  @Override
  public Runnable createAddPostCommand(final String userName, final String postText) {
    return new AddPostCommand(userManager, userName, postText);
  }

  @Override
  public Runnable createFollowUserCommand(final String userName, final String followerUserName) {
    return new FollowUserCommand(userManager, userName, followerUserName);
  }

  @Override
  public Runnable createDisplayTimelineCommand(final String userName) {
    return new DisplayTimelineCommand(userManager, userName, output, clock);
  }

  @Override
  public Runnable createDisplayWallCommand(final String userName) {
    return new DisplayWallCommand(userManager, userName, output, clock);
  }

  @Override
  public Runnable createNullCommand() {
    return NullCommand.getInstance();
  }
}
