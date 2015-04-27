package org.krzogr.chirper.command.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import org.krzogr.chirper.service.UserManager;

/**
 * Represents the command which adds new post to the specified user.
 */
public final class AddPostCommand implements Runnable {
  private final UserManager userManager;
  private final String userName;
  private final String postText;
  private final LocalDateTime creationTime;

  public AddPostCommand(final UserManager userManager, final String userName,
      final String postText, final LocalDateTime creationTime) {
    Objects.requireNonNull(userManager);
    Objects.requireNonNull(userName);
    Objects.requireNonNull(postText);
    Objects.requireNonNull(creationTime);

    this.userManager = userManager;
    this.userName = userName;
    this.postText = postText;
    this.creationTime = creationTime;
  }

  @Override
  public void run() {
    userManager.getOrCreateUser(userName).addPost(postText, creationTime);
  }
}
