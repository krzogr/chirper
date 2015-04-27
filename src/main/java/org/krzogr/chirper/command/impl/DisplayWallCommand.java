package org.krzogr.chirper.command.impl;

import java.io.PrintStream;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Objects;

import org.krzogr.chirper.service.UserManager;
import org.krzogr.chirper.util.PostFormatter;
import org.krzogr.chirper.util.PostWriter;

/**
 * Represents the command which prints user wall posts.
 * <p>
 * Wall posts contain all posts of the user plus all posts of the followed
 * users.
 * </p>
 */
public final class DisplayWallCommand implements Runnable {
  private final UserManager userManager;
  private final String userName;
  private final PrintStream output;
  private final Clock clock;

  public DisplayWallCommand(final UserManager userManager,
      final String userName, final PrintStream output, final Clock clock) {
    Objects.requireNonNull(userManager);
    Objects.requireNonNull(userName);
    Objects.requireNonNull(output);
    Objects.requireNonNull(clock);

    this.userManager = userManager;
    this.userName = userName;
    this.output = output;
    this.clock = clock;
  }

  @Override
  public void run() {
    PostWriter writer = new PostWriter(output, LocalDateTime.now(clock),
        new PostFormatter(true, true));
    
    userManager.getOrCreateUser(userName).getWall()
        .forEach(p -> writer.write(p));
  }
}