package org.krzogr.chirper.command.impl;

import static org.junit.Assert.*;
import org.krzogr.chirper.command.CommandFactory;
import org.krzogr.chirper.command.CommandParser;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Unit test for command parser implementation.
 */
public class CommandParserTest {
  private CommandFactory commandFactory;
  private CommandParser commandParser;

  @Before
  public void prepare() {
    commandFactory = mock(CommandFactory.class);
    commandParser = new CommandParserImpl(commandFactory);
  }

  @Test
  public void testAddPostCommand() {
    commandParser.parse("Alice -> test");
    verify(commandFactory).createAddPostCommand("Alice", "test");

    commandParser.parse("Alice -> long sentence with special chars .,;-%");
    verify(commandFactory).createAddPostCommand("Alice",
        "long sentence with special chars .,;-%");

    commandParser.parse("Alice ->    text to trim  - .    ");
    verify(commandFactory).createAddPostCommand("Alice", "text to trim  - .");

    commandParser.parse("   Long Username to trim   ->   text to trim  - .  ");
    verify(commandFactory).createAddPostCommand("Long Username to trim",
        "text to trim  - .");

    assertInvalidCommand("     ->    text");
    assertInvalidCommand(" a    ->    ");
    assertInvalidCommand("a -> ");
    assertInvalidCommand("     ->    ");
  }

  @Test
  public void testAddFollowerCommand() {
    commandParser.parse("Alice follows test");
    verify(commandFactory).createFollowUserCommand("Alice", "test");

    commandParser.parse("Alice follows long username with special chars .,;-%");
    verify(commandFactory).createFollowUserCommand("Alice",
        "long username with special chars .,;-%");

    commandParser.parse("Alice follows     username to trim  - .    ");
    verify(commandFactory).createFollowUserCommand("Alice",
        "username to trim  - .");

    commandParser
        .parse("     Long Username to trim   follows    Longer Username to trim    ");
    verify(commandFactory).createFollowUserCommand("Long Username to trim",
        "Longer Username to trim");

    assertInvalidCommand("     follows    text");
    assertInvalidCommand(" a    follows    ");
    assertInvalidCommand("a follows ");
    assertInvalidCommand("     follows    ");
  }

  @Test
  public void testShowUserPostsCommand() {
    commandParser.parse("Alice");
    verify(commandFactory).createDisplayTimelineCommand("Alice");

    commandParser.parse("    Lisa  ");
    verify(commandFactory).createDisplayTimelineCommand("Lisa");

    commandParser.parse("     Long Username to trim  - .     ");
    verify(commandFactory).createDisplayTimelineCommand(
        "Long Username to trim  - .");
  }

  @Test
  public void testShowWallPostsCommand() {
    commandParser.parse("Alice wall  ");
    verify(commandFactory).createDisplayWallCommand("Alice");

    commandParser.parse("     Long Username to trim   wall");
    verify(commandFactory).createDisplayWallCommand("Long Username to trim");

    assertInvalidCommand(" Alice wall text");
    assertInvalidCommand(" Alice wall/");
    assertInvalidCommand("     wall    text");
    assertInvalidCommand("     wall    ");

    commandParser.parse("Alice walls");
    verify(commandFactory).createDisplayTimelineCommand("Alice walls");
  }

  @Test
  public void testNullCommand() {
    commandParser.parse("");
    commandParser.parse("  ");
    verify(commandFactory, times(2)).createNullCommand();
  }

  private void assertInvalidCommand(String command) {
    IllegalArgumentException error = null;
    try {
      commandParser.parse(command);
    } catch (IllegalArgumentException e) {
      error = e;
    }

    assertNotNull("Expected invalid command but the command was parsed: '"
        + command + "'", error);
    assertEquals("Invalid command '" + command + "'", error.getMessage());
  }
}
