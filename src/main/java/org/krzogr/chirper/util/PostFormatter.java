package org.krzogr.chirper.util;

import static java.time.Duration.between;

import java.time.LocalDateTime;

import org.krzogr.chirper.service.Post;

/**
 * Utility class to format Post to a user friendly string.
 */
public final class PostFormatter {
  private final boolean includeUserName;
  private final boolean highestDurationUnitOnly;

  public PostFormatter(final boolean includeUserName,
      final boolean highestDurationUnitOnly) {
    this.includeUserName = includeUserName;
    this.highestDurationUnitOnly = highestDurationUnitOnly;
  }

  public String format(final Post post, final LocalDateTime referenceTime) {
    StringBuilder buffer = new StringBuilder();

    if (includeUserName) {
      buffer.append(post.getUser().getUserName()).append(" - ");
    }

    buffer.append(post.getText());
    buffer.append(" (");
    buffer.append(formatPostAge(post, referenceTime));
    buffer.append(" ago)");

    return buffer.toString();
  }

  private String formatPostAge(final Post post,
      final LocalDateTime referenceTime) {
    return DurationFormatter
        .format(between(post.getCreationTime(), referenceTime),
            highestDurationUnitOnly);
  }
}
