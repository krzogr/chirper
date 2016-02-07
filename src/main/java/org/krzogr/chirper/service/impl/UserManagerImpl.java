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

package org.krzogr.chirper.service.impl;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.krzogr.chirper.service.User;
import org.krzogr.chirper.service.UserManager;

/** Default, in-memory and <b>single threaded</b> implementation of UserManager. */
public final class UserManagerImpl implements UserManager {
  private final Map<String, User> users = new HashMap<String, User>();
  private final Clock clock;
  private long nextUserId = 1;
  private long nextPostId = 1;

  public UserManagerImpl(final Clock clock) {
    this.clock = clock;
  }

  @Override
  public User getOrCreateUser(final String userName) {
    Objects.requireNonNull(userName);
    return users.computeIfAbsent(userName, name -> new UserImpl(nextUserId(), name, this));
  }

  public long nextUserId() {
    return nextUserId++;
  }

  public long nextPostId() {
    return nextPostId++;
  }

  public Clock getClock() {
    return clock;
  }
}
