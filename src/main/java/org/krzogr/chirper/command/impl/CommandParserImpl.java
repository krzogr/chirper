package org.krzogr.chirper.command.impl;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.krzogr.chirper.command.CommandFactory;
import org.krzogr.chirper.command.CommandParser;

/**
 * Default implementation of command parser.
 */
public final class CommandParserImpl implements CommandParser {
	private final CommandFactory commandFactory;
	private final Pattern commandPattern;
	
	public CommandParserImpl(final CommandFactory commandFactory) {
		Objects.requireNonNull(commandFactory);
		
		this.commandFactory = commandFactory;
		this.commandPattern = Pattern.compile("^(.+?)( -> | follows | wall\\b)(.+)?$");
	}

	@Override
	public Runnable parse(final String line) {
		Matcher matcher = commandPattern.matcher(line);
		
		if(matcher.matches()) {
			String userName = matcher.group(1).trim();
			String command = matcher.group(2);
			String commandArgs = matcher.group(3) != null ? matcher.group(3).trim() : "";
			
			if(userName.length() > 0) {
				if(command.equals(" -> ") && commandArgs.length() > 0) {
					return commandFactory.createAddPostCommand(userName, commandArgs);
				} else if(command.equals(" follows ") && commandArgs.length() > 0) {
					return commandFactory.createFollowUserCommand(userName, commandArgs);
				} else if(command.equals(" wall") && commandArgs.length() == 0) {
					return commandFactory.createDisplayWallCommand(userName);
				} 
			}
			
			throw new IllegalArgumentException("Invalid command '" + line + "'");
		} else {
			String userName = line.trim();
			if(userName.length() > 0) {
				return commandFactory.createDisplayTimelineCommand(userName);
			} else {
				return commandFactory.createNullCommand();
			}
		}
	}
}
