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

package org.krzogr.chirper.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import org.krzogr.chirper.command.CommandParser;

/** Chirper application command processing loop.
 * <p>
 * Reads commands from the specified input and executes them. Command loop
 * (application) is terminated when there is no more input.
 * </p> */
public final class ChirperApp implements Runnable {
  private final BufferedReader commandInput;
  private final CommandParser commandParser;
  private final PrintStream errorStream;

  public ChirperApp(final BufferedReader commandInput, final CommandParser commandParser, final PrintStream errorStream) {
    this.commandInput = commandInput;
    this.commandParser = commandParser;
    this.errorStream = errorStream;
  }

  @Override
  public void run() {
    String line;

    while (true) {
      try {
        line = commandInput.readLine();

        if (line == null) {
          // No more input - terminate application
          return;
        }

        Runnable command = commandParser.parse(line);
        command.run();
      } catch (IOException | RuntimeException e) {
        String message = e.getClass().getSimpleName() + " " + e.getMessage();
        errorStream.println("ERROR: " + message);
      }
    }
  }
}
