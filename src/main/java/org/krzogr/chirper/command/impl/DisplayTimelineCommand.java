package org.krzogr.chirper.command.impl;

import java.io.PrintStream;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Objects;

import org.krzogr.chirper.service.UserManager;
import org.krzogr.chirper.util.PostFormatter;
import org.krzogr.chirper.util.PostWriter;

/**
 * Represents the command which displays user timeline posts.
 * <p>
 * Timeline posts are the posts which were sent by the user.
 * </p>
 */
public final class DisplayTimelineCommand implements Runnable {
	private final UserManager userManager;
	private final String userName;
	private final PrintStream output;
	private final Clock clock;

	public DisplayTimelineCommand(final UserManager userManager, final String userName,
			                      final PrintStream output, final Clock clock) {
		Objects.requireNonNull(userManager);
		Objects.requireNonNull(userName);
		Objects.requireNonNull(output);
		Objects.requireNonNull(clock);
		
		this.userManager = userManager;
		this.userName = userName;
		this.output = output;
		this.clock = clock;
	}

	@Override
	public void run() {
		PostWriter writer = new PostWriter(output, LocalDateTime.now(clock), new PostFormatter(false, true));
		userManager.getOrCreateUser(userName).getTimeline().forEach(p -> writer.write(p));		
	}
}