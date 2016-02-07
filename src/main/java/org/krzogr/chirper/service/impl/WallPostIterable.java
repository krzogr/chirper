package org.krzogr.chirper.service.impl;

import java.util.Iterator;
import java.util.List;

import org.krzogr.chirper.service.Post;

/** Iterable responsible for sorting and aggregating wall posts. */
public final class WallPostIterable implements Iterable<Post> {
  private final List<Iterable<Post>> iterables;

  public WallPostIterable(final List<Iterable<Post>> iterables) {
    this.iterables = iterables;
  }

  @Override
  public Iterator<Post> iterator() {
    return new WallPostIterator(iterables);
  }
}
