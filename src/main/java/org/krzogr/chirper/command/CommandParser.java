package org.krzogr.chirper.command;

/**
 * Responsible for parsing the command entered by the user and creating the
 * command.
 */
public interface CommandParser {
  /**
   * Parses and creates the command from user input.
   * 
   * @param input User input.
   * @return Command which corresponds to the user input.
   * @throws IllegalArgumentException If the specified input is invalid.
   */
  Runnable parse(String input);
}
