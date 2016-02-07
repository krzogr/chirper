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
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.krzogr.chirper.service.Post;
import org.krzogr.chirper.service.User;

/** Unit test to verify the default implementation of UserManager. */
public class UserManagerImplTest {
  private Clock clock;
  private UserManagerImpl userManager;

  @Before
  public void prepare() {
    clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    userManager = new UserManagerImpl(clock);
  }

  @Test
  public void testUserCreation() {
    Set<Long> generatedIds = new HashSet<Long>();

    for (int i = 0; i < 100; i++) {
      User user = userManager.getOrCreateUser("u_" + i);

      assertNotNull(user);
      generatedIds.add(user.getId());

      assertEquals("u_" + i, user.getUserName());
      assertNotNull(user.getTimeline());
      assertNotNull(user.getWall());
    }

    // Verify that all generated ids are unique
    assertEquals(100, generatedIds.size());
  }

  @Test
  public void testPostCreation() {
    Set<Long> generatedIds = new HashSet<Long>();
    User user = userManager.getOrCreateUser("Alice");

    for (int i = 0; i < 100; i++) {
      Post post = user.addPost("post_" + i);

      assertNotNull(post);
      generatedIds.add(post.getId());

      assertEquals("post_" + i, post.getText());
      assertEquals(LocalDateTime.now(clock), post.getCreationTime());
      assertEquals(user, post.getUser());
    }

    // Verify that all generated ids are unique
    assertEquals(100, generatedIds.size());
  }
}
