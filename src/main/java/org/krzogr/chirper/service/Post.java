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

package org.krzogr.chirper.service;

import java.time.LocalDateTime;

/** Represents a post in Chirper application.
 * <p>
 * Post is always associated with a single user and has unique identifier.
 * </p> */
public interface Post {
  /** Returns unique post identifier.
   *
   * @return Unique post identifier. **/
  long getId();

  /** Returns post text.
   *
   * @return Post text. **/
  String getText();

  /** Returns the user who sent this post.
   *
   * @return User who sent this post. **/
  User getUser();

  /** Returns post creation time.
   *
   * @return Post creation time. **/
  LocalDateTime getCreationTime();
}
