package org.krzogr.chirper.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.krzogr.chirper.service.Post;
import org.krzogr.chirper.service.User;

/**
 * Unit test to verify the default implementation of UserManager.
 */
public class UserManagerImplTest {
  private UserManagerImpl userManager;

  @Before
  public void prepare() {
    userManager = new UserManagerImpl();
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
    LocalDateTime dateTime = LocalDateTime.now();

    for (int i = 0; i < 100; i++) {
      Post post = user.addPost("post_" + i, dateTime);

      assertNotNull(post);
      generatedIds.add(post.getId());

      assertEquals("post_" + i, post.getText());
      assertEquals(dateTime, post.getCreationTime());
      assertEquals(user, post.getUser());
    }

    // Verify that all generated ids are unique
    assertEquals(100, generatedIds.size());
  }
}
