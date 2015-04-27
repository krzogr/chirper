package org.krzogr.chirper.command.impl;

import java.io.PrintStream;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Objects;

import org.krzogr.chirper.command.CommandFactory;
import org.krzogr.chirper.service.UserManager;

/**
 * Default implementation of command factory.
 */
public final class CommandFactoryImpl implements CommandFactory {
  private final UserManager userManager;
  private final PrintStream output;
  private final Clock clock;

  public CommandFactoryImpl(final UserManager userManager,
      final PrintStream output, final Clock clock) {
    Objects.requireNonNull(userManager);
    Objects.requireNonNull(output);
    Objects.requireNonNull(clock);

    this.userManager = userManager;
    this.output = output;
    this.clock = clock;
  }

  @Override
  public Runnable createAddPostCommand(final String userName,
      final String postText) {
    return new AddPostCommand(userManager, userName, postText,
        LocalDateTime.now(clock));
  }

  @Override
  public Runnable createFollowUserCommand(final String userName,
      final String followerUserName) {
    return new FollowUserCommand(userManager, userName, followerUserName);
  }

  @Override
  public Runnable createDisplayTimelineCommand(final String userName) {
    return new DisplayTimelineCommand(userManager, userName, output, clock);
  }

  @Override
  public Runnable createDisplayWallCommand(final String userName) {
    return new DisplayWallCommand(userManager, userName, output, clock);
  }

  @Override
  public Runnable createNullCommand() {
    return NullCommand.getInstance();
  }
}
