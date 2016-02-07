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

package org.krzogr.chirper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.time.Clock;

import org.krzogr.chirper.app.ChirperApp;
import org.krzogr.chirper.command.CommandFactory;
import org.krzogr.chirper.command.CommandParser;
import org.krzogr.chirper.command.impl.DefaultCommandFactory;
import org.krzogr.chirper.command.impl.RegexpCommandParser;
import org.krzogr.chirper.service.impl.UserManagerImpl;

/** Main class which starts the Chirper console application.
 * <p>
 * Application will read commands from standard input and write results to
 * standard output. To terminate the application the user needs to press Ctrl+C.
 * </p> */
public final class Main {
  public static void main(final String[] args) {
    Clock clock = Clock.systemDefaultZone();

    BufferedReader commandInput = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));

    CommandFactory commandFactory = new DefaultCommandFactory(new UserManagerImpl(clock), System.out, clock);

    CommandParser commandParser = new RegexpCommandParser(commandFactory);

    new ChirperApp(commandInput, commandParser, System.err).run();
  }
}
