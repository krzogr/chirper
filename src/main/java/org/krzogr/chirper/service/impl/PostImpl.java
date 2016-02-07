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

import java.time.LocalDateTime;
import java.util.Objects;

import org.krzogr.chirper.service.Post;
import org.krzogr.chirper.service.User;

/** Default implementation of Post. */
public final class PostImpl implements Post {
  private final long id;
  private final String text;
  private final User user;
  private final LocalDateTime creationTime;

  public PostImpl(final long id, final String text, final User user, final LocalDateTime creationTime) {
    Objects.requireNonNull(text);
    Objects.requireNonNull(user);
    Objects.requireNonNull(creationTime);

    this.id = id;
    this.text = text;
    this.user = user;
    this.creationTime = creationTime;
  }

  @Override
  public long getId() {
    return id;
  }

  @Override
  public String getText() {
    return text;
  }

  @Override
  public User getUser() {
    return user;
  }

  @Override
  public LocalDateTime getCreationTime() {
    return creationTime;
  }
}
