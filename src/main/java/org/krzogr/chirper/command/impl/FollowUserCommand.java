package org.krzogr.chirper.command.impl;

import java.util.Objects;

import org.krzogr.chirper.service.UserManager;

/** Represents the command which makes a user to follow another user. */
public final class FollowUserCommand implements Runnable {
  private final UserManager userManager;
  private final String userName;
  private final String userToFollow;

  public FollowUserCommand(final UserManager userManager, final String userName, final String userToFollow) {
    Objects.requireNonNull(userManager);
    Objects.requireNonNull(userName);
    Objects.requireNonNull(userToFollow);

    this.userManager = userManager;
    this.userName = userName;
    this.userToFollow = userToFollow;
  }

  @Override
  public void run() {
    userManager.getOrCreateUser(userName).followUser(userManager.getOrCreateUser(userToFollow));
  }
}
