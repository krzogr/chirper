package org.krzogr.chirper.service.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import org.krzogr.chirper.service.Post;
import org.krzogr.chirper.service.User;

/**
 * Default implementation of Post.
 */
public final class PostImpl implements Post {
  private final long id;
  private final String text;
  private final User user;
  private final LocalDateTime creationTime;

  public PostImpl(final long id, final String text, final User user,
      final LocalDateTime creationTime) {
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
