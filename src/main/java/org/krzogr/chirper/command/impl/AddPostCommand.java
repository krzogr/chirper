package org.krzogr.chirper.command.impl;

import java.util.Objects;

import org.krzogr.chirper.service.UserManager;

/** Command which adds new post for the specified user. */
public final class AddPostCommand implements Runnable {
  private final UserManager userManager;
  private final String userName;
  private final String postText;

  public AddPostCommand(final UserManager userManager, final String userName, final String postText) {
    Objects.requireNonNull(userManager);
    Objects.requireNonNull(userName);
    Objects.requireNonNull(postText);

    this.userManager = userManager;
    this.userName = userName;
    this.postText = postText;
  }

  @Override
  public void run() {
    userManager.getOrCreateUser(userName).addPost(postText);
  }
}
