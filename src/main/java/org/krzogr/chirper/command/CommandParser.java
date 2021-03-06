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

/** Responsible for parsing input from the user and creating the command. */
public interface CommandParser {
  /** Parses and creates the command from user input.
   * 
   * @param input User input.
   * @return Command which corresponds to the user input.
   * @throws IllegalArgumentException If the specified input is invalid. */
  Runnable parse(String input);
}
