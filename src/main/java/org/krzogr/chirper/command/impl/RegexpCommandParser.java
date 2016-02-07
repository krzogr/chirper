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

package org.krzogr.chirper.command.impl;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.krzogr.chirper.command.CommandFactory;
import org.krzogr.chirper.command.CommandParser;

/** Default implementation of command parser. */
public final class RegexpCommandParser implements CommandParser {
  private final CommandFactory commandFactory;
  private final Pattern commandPattern;

  public RegexpCommandParser(final CommandFactory commandFactory) {
    Objects.requireNonNull(commandFactory);

    this.commandFactory = commandFactory;
    this.commandPattern = Pattern.compile("^(.+?)( -> | follows | wall\\b)(.+)?$");
  }

  @Override
  public Runnable parse(final String line) {
    Matcher matcher = commandPattern.matcher(line);

    if (matcher.matches()) {
      String userName = matcher.group(1).trim();
      String command = matcher.group(2);
      String group3 = matcher.group(3);
      String commandArgs = group3 != null ? group3.trim() : "";

      if (userName.length() > 0) {
        if (command.equals(" -> ") && commandArgs.length() > 0) {
          return commandFactory.createAddPostCommand(userName, commandArgs);
        } else if (command.equals(" follows ") && commandArgs.length() > 0) {
          return commandFactory.createFollowUserCommand(userName, commandArgs);
        } else if (command.equals(" wall") && commandArgs.length() == 0) {
          return commandFactory.createDisplayWallCommand(userName);
        }
      }

      throw new IllegalArgumentException("Invalid command '" + line + "'");
    } else {
      String userName = line.trim();
      if (userName.length() > 0) {
        return commandFactory.createDisplayTimelineCommand(userName);
      } else {
        return commandFactory.createNullCommand();
      }
    }
  }
}
