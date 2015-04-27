package org.krzogr.chirper.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.krzogr.chirper.service.User;
import org.krzogr.chirper.service.UserManager;

/**
 * Default, in-memory and <b>single threaded</b> implementation of UserManager.
 */
public final class UserManagerImpl implements UserManager {
  private final Map<String, User> users = new HashMap<String, User>();
  private long nextUserId = 1;
  private long nextPostId = 1;

  @Override
  public User getOrCreateUser(final String userName) {
    Objects.requireNonNull(userName);
    return users.computeIfAbsent(userName, name -> new UserImpl(nextUserId(),
        name, this));
  }

  public long nextUserId() {
    return nextUserId++;
  }

  public long nextPostId() {
    return nextPostId++;
  }
}
