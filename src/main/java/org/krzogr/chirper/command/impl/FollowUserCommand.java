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

import java.util.Objects;

import org.krzogr.chirper.service.UserManager;

/** Represents the command which makes a user to follow another user. */
public final class FollowUserCommand implements Runnable {
  private final UserManager userManager;
  private final String userName;
  private final String userToFollow;

  public FollowUserCommand(final UserManager userManager, final String userName, final String userToFollow) {
    Objects.requireNonNull(userManager);
    Objects.requireNonNull(userName);
    Objects.requireNonNull(userToFollow);

    this.userManager = userManager;
    this.userName = userName;
    this.userToFollow = userToFollow;
  }

  @Override
  public void run() {
    userManager.getOrCreateUser(userName).followUser(userManager.getOrCreateUser(userToFollow));
  }
}
