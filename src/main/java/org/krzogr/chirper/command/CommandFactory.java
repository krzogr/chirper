/*
 * Copyright (C) 2015 krzogr (krzogr@gmail.com)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.krzogr.chirper.command;

/** Factory used to create commands in Chirper application. */
public interface CommandFactory {
  /** Creates the command to add new post to the specified user.
   * 
   * @param userName User name
   * @param postText Text of the post. **/
  Runnable createAddPostCommand(String userName, String postText);

  /** Creates the command to follow the specified user.
   * 
   * @param userName User name
   * @param userToFollow Name of the user to follow.
   * @return Command to follow the specified user. */
  Runnable createFollowUserCommand(String userName, String userToFollow);

  /** Creates the command to show all timeline posts of the specified user.
   * 
   * @param userName User name
   * @return Command to show all timeline posts of the specified user. */
  Runnable createDisplayTimelineCommand(String userName);

  /** Creates the command to show all wall posts of the specified user.
   * <p>
   * Wall posts contain all posts of the user plus all posts of the followed
   * users.
   * </p>
   * 
   * @param userName User name
   * @return Command to show all wall posts of the specified user. */
  Runnable createDisplayWallCommand(String userName);

  /** Creates NULL command.
   * <p>
   * Used to represent the scenario when the user did not enter anything.
   * </p>
   * 
   * @return NULL command. */
  Runnable createNullCommand();
}
