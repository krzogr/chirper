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
import org.krzogr.chirper.service.Post;
import org.krzogr.chirper.service.User;

/** Dummy implementation of Post used for testing. */
public class DummyPostImpl implements Post {
  private long id;
  private String text;
  private LocalDateTime creationTime;

  public DummyPostImpl(long id, String text, LocalDateTime creationTime) {
    this.id = id;
    this.text = text;
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
    return null;
  }

  @Override
  public LocalDateTime getCreationTime() {
    return creationTime;
  }
}
