package org.krzogr.chirper.command;

/**
 * Factory used to create commands in Chirper application.
 * <p>
 * Commands need to be executed to take effect.
 * </p>
 */
public interface CommandFactory {
  /**
   * Creates the command to add new post to the specified user.
   * 
   * @param userName User name
   * @param postText Text of the post.
   * @return Command to add new post to the specified user.
   */
  Runnable createAddPostCommand(String userName, String postText);

  /**
   * Creates the command to follow the specified user.
   * 
   * @param userName User name
   * @param userToFollow Name of the user to follow.
   * @return Command to follow the specified user.
   */
  Runnable createFollowUserCommand(String userName, String userToFollow);

  /**
   * Creates the command to show all timeline posts of the specified user.
   * 
   * @param userName User name
   * @return Command to show all timeline posts of the specified user.
   */
  Runnable createDisplayTimelineCommand(String userName);

  /**
   * Creates the command to show all wall posts of the specified user.
   * <p>
   * Wall posts contain all posts of the user plus all posts of the followed
   * users.
   * </p>
   * 
   * @param userName User name
   * @return Command to show all wall posts of the specified user.
   */
  Runnable createDisplayWallCommand(String userName);

  /**
   * Creates NULL command.
   * <p>
   * Used to represent the scenario when the user did not enter anything.
   * </p>
   * 
   * @return NULL command.
   */
  Runnable createNullCommand();
}
