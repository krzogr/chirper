package org.krzogr.chirper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.time.Clock;

import org.krzogr.chirper.app.ChirperApp;
import org.krzogr.chirper.command.CommandFactory;
import org.krzogr.chirper.command.CommandParser;
import org.krzogr.chirper.command.impl.CommandFactoryImpl;
import org.krzogr.chirper.command.impl.CommandParserImpl;
import org.krzogr.chirper.service.impl.UserManagerImpl;

/**
 * Main class which starts the Chirper console application.
 * <p>
 * Application will read commands from standard input and write results to
 * standard output. To terminate the application the user needs to press Ctrl+C.
 * </p>
 */
public final class Main {
  public static void main(final String[] args) {
    BufferedReader commandInput = new BufferedReader(new InputStreamReader(
        System.in, Charset.defaultCharset()));

    CommandFactory commandFactory = new CommandFactoryImpl(
        new UserManagerImpl(), System.out, Clock.systemDefaultZone());

    CommandParser commandParser = new CommandParserImpl(commandFactory);

    new ChirperApp(commandInput, commandParser, System.err).run();
  }
}
