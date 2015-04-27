package org.krzogr.chirper.service.impl;

import java.time.LocalDateTime;
import org.krzogr.chirper.service.Post;
import org.krzogr.chirper.service.User;

/**
 * Dummy implementation of Post used for testing.
 */
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
