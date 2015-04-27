package org.krzogr.chirper.service;

import java.time.LocalDateTime;

/**
 * Represents a post in Chirper application.
 * <p>
 * Post is always associated with a single user and is identified by unique
 * identifier.
 * </p>
 */
public interface Post {
  /**
   * Returns unique post identifier.
   * 
   * @return Unique post identifier.
   */
  long getId();

  /**
   * Returns post text.
   * 
   * @return Post text.
   */
  String getText();

  /**
   * Returns the user who sent this post.
   * 
   * @return User who sent this post.
   */
  User getUser();

  /**
   * Returns post creation time.
   * 
   * @return Post creation time.
   */
  LocalDateTime getCreationTime();
}
