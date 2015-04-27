package org.krzogr.chirper.util;

import java.io.PrintStream;
import java.time.LocalDateTime;

import org.krzogr.chirper.service.Post;

/**
 * Utility class to facilitate writing posts to output streams.
 */
public final class PostWriter {
	private final PrintStream output;
	private final LocalDateTime referenceTime;
	private final PostFormatter formatter;
	
	public PostWriter(final PrintStream output, final LocalDateTime referenceTime, final PostFormatter formatter) {
		this.output = output;
		this.referenceTime = referenceTime;
		this.formatter = formatter;
	}

	public void write(final Post post) {
		output.println(formatter.format(post, referenceTime));
	}
}
