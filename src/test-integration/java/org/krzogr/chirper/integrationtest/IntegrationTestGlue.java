package org.krzogr.chirper.integrationtest;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.TimeZone;

import static org.junit.Assert.*;

import org.krzogr.chirper.app.ChirperApp;
import org.krzogr.chirper.command.CommandFactory;
import org.krzogr.chirper.command.CommandParser;
import org.krzogr.chirper.command.impl.CommandFactoryImpl;
import org.krzogr.chirper.command.impl.CommandParserImpl;
import org.krzogr.chirper.service.UserManager;
import org.krzogr.chirper.service.impl.ManualClock;
import org.krzogr.chirper.service.impl.UserManagerImpl;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Helper class used to glue together cucumber integration tests with java
 * classes.
 * <p>
 * This class will be discovered on the classpath by cucumber dynamically. 
 * </p>
 */
public class IntegrationTestGlue {
  private TimeZone defaultTimeZone;
  private ManualClock clock;
  private UserManager userManager;
  private StringBuilder commandOutput;
  private StringBuilder commandErrors;

  @Before
  public void prepare() {
    defaultTimeZone = TimeZone.getDefault();
    initTestEnv();
  }

  @After
  public void cleanup() {
    TimeZone.setDefault(defaultTimeZone);

    assertEquals("Unexpected output '" + commandOutput + "'", 0,
        commandOutput.length());
    assertEquals("Unexpected errors '" + commandErrors + "'", 0,
        commandErrors.length());
  }

  private void initTestEnv() {
    clock = new ManualClock();
    userManager = new UserManagerImpl();
    commandOutput = new StringBuilder();
    commandErrors = new StringBuilder();
  }

  private void runCommand(String command) {
    try {
      BufferedReader input = new BufferedReader(new StringReader(
          command.toString() + System.lineSeparator()));

      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      CommandFactory commandFactory = new CommandFactoryImpl(userManager,
          new PrintStream(outputStream), clock);
      CommandParser commandParser = new CommandParserImpl(commandFactory);

      ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
      new ChirperApp(input, commandParser, new PrintStream(errorStream)).run();

      outputStream.close();
      byte[] outputBytes = outputStream.toByteArray();
      if (outputBytes.length > 0) {
        commandOutput.append(new String(outputBytes));
      }

      errorStream.close();
      byte[] errorBytes = errorStream.toByteArray();
      if (errorBytes.length > 0) {
        commandErrors.append(new String(errorBytes));
      }
    } catch (IOException e) {
      throw new RuntimeException("Unexpected error while running command", e);
    }
  }

  @Given("^application starts on \"(.*?)\" at \"(.*?)\" in \"(.*?)\" timezone$")
  public void givenApplicationStarts(String date, String time, String timezoneID)
      throws Throwable {
    TimeZone timeZone = TimeZone.getTimeZone(timezoneID);
    TimeZone.setDefault(timeZone);
    initTestEnv();
    clock.setDateTime(date, time);
  }

  @Given("^current date is \"(.*?)\" and current time is \"(.*?)\"$")
  public void givenCurrentDateTimeIs(String date, String time) throws Throwable {
    clock.setDateTime(date, time);
  }

  @When("^user types the text \"(.*?)\"$")
  public void whenUserTypesText(String command) throws Throwable {
    runCommand(command);
  }

  @When("^user types at \"(.*?)\" the text \"(.*?)\"$")
  public void whenUserTypesTextAtTime(String time, String command)
      throws Throwable {
    clock.setTime(time);
    runCommand(command);
  }

  @When("^user types on \"(.*?)\" at \"(.*?)\" the text \"(.*?)\"$")
  public void whenUserTypesTextOnDateAtTime(String date, String time,
      String command) throws Throwable {
    clock.setDateTime(date, time);
    runCommand(command);
  }

  @Then("^user sees$")
  public void thenUserSeesMultipleLines(String output) throws Throwable {
    assertEquals(prepareExpectedOutput(output), commandOutput.toString());
    commandOutput.setLength(0);
  }

  @Then("^user sees \"(.*?)\"$")
  public void thenUserSeesLine(String output) throws Throwable {
    assertEquals(prepareExpectedOutput(output), commandOutput.toString());
    commandOutput.setLength(0);
  }

  private String prepareExpectedOutput(String output) {
    String result = output.replaceAll("\n", System.lineSeparator());
    result = result + System.lineSeparator();
    return result;
  }
  
  @Then("^user sees no output$")
  public void thenUserSeesNoOutput() throws Throwable {
    assertEquals("", commandOutput.toString());
  }

  @Then("^error \"(.*?)\" is shown$")
  public void thenErrorIsShown(String errorMsg) throws Throwable {
    assertEquals(commandErrors.toString(), errorMsg + System.lineSeparator());
    commandErrors.setLength(0);
  }
}
