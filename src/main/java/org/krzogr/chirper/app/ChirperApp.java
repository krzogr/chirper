package org.krzogr.chirper.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import org.krzogr.chirper.command.CommandParser;

/**
 * Represents Chirper application command processing loop.
 * <p>
 * Reads commands from the specified input and executes them. Command loop
 * (application) is terminated when there is no more input.
 * </p>
 */
public final class ChirperApp implements Runnable {
  private final BufferedReader commandInput;
  private final CommandParser commandParser;
  private final PrintStream errorStream;

  public ChirperApp(final BufferedReader commandInput,
      final CommandParser commandParser, final PrintStream errorStream) {
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
