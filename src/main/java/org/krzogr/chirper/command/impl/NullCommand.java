package org.krzogr.chirper.command.impl;

/** Represents the empty command. */
public final class NullCommand implements Runnable {
  private static final NullCommand INSTANCE = new NullCommand();

  public static NullCommand getInstance() {
    return INSTANCE;
  }

  @Override
  public void run() {
    // Do nothing
  }
}
