package org.krzogr.chirper.service.impl;

import java.util.Iterator;
import java.util.List;

import org.krzogr.chirper.service.Post;

/**
 * Iterable responsible for sorting and aggregating wall posts.
 */
public final class WallPostIterable implements Iterable<Post> {
	private final List<Iterator<Post>> iterators;
	
	public WallPostIterable(final List<Iterator<Post>> iterators) {
		this.iterators = iterators;
	}

	@Override
	public Iterator<Post> iterator() {
		return new WallPostIterator(iterators);
	}
}
