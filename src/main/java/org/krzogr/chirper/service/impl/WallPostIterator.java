package org.krzogr.chirper.service.impl;

import java.util.Iterator;
import java.util.List;

import org.krzogr.chirper.service.Post;

/**
 * Single threaded sorting iterator for wall posts.
 * <p>
 * This class uses heap in order to aggregate and sort multiple post iterators
 * into one stream of posts sorted by creation time.
 * </p>
 */
public final class WallPostIterator implements Iterator<Post> {
  private final PostRef[] heap;
  private int end;

  /**
   * Wrapper on top of post iterator in order to facilitate heap operations.
   */
  private static class PostRef {
    private final Iterator<Post> iterator;
    private Post currentPost;

    public PostRef(final Iterator<Post> iterator) {
      this.iterator = iterator;
      if (iterator.hasNext()) {
        this.currentPost = iterator.next();
      }
    }

    public boolean hasPost() {
      return currentPost != null;
    }

    public Post getPost() {
      return currentPost;
    }

    public void moveToNextPost() {
      currentPost = null;
      if (iterator.hasNext()) {
        this.currentPost = iterator.next();
      }
    }
  }

  public WallPostIterator(final List<Iterable<Post>> iterables) {
    heap = new PostRef[iterables.size() == 0 ? 1 : iterables.size()];
    initHeap(iterables);
  }

  private void initHeap(final List<Iterable<Post>> iterables) {
    // Copy all elements to the heap
    int index = 0;
    for (Iterable<Post> iterable : iterables) {
      PostRef postRef = new PostRef(iterable.iterator());
      if (postRef.hasPost()) {
        heap[index++] = postRef;
      }
    }

    // Shift elements to establish heap condition (shape)
    int start = (int) Math.floor(((index - 2)) / 2.0);
    while (start >= 0) {
      shiftDown(start, index - 1);
      start = start - 1;
    }

    // Remember the last valid heap element
    end = index - 1;
  }

  @Override
  public boolean hasNext() {
    return heap[0] != null && heap[0].hasPost();
  }

  @Override
  public Post next() {
    Post result = heap[0].getPost();

    // Since we're returning the current post we have to move to the next
    heap[0].moveToNextPost();

    if (!heap[0].hasPost()) {
      // If there is no next post in this PostRef then we can get rid of it
      heap[0] = heap[end--];
    }

    // Rebuild the heap
    shiftDown(0, end);

    return result;
  }

  /**
   * Places the start element in correct position in the heap defined between
   * the start and end elements.
   * <p>
   * After calling this function the elements [start, end] will form a valid
   * heap.
   * </p>
   */
  private void shiftDown(final int start, final int end) {
    int root = start;

    while (root * 2 + 1 <= end) {
      // Keep shifting elements as long as the root has at least one child
      int child = root * 2 + 1;
      int swap = root;

      if (isBefore(heap[swap], heap[child])) {
        swap = child;
      }

      // If there is a right child and that child is greater
      if (child + 1 <= end && isBefore(heap[swap], heap[child + 1])) {
        swap = child + 1;
      }

      if (swap == root) {
        // The root holds the largest element - shift is done
        return;
      } else {
        // Swap the *root* element with the *swap* element
        PostRef tmp = heap[root];
        heap[root] = heap[swap];
        heap[swap] = tmp;

        // Repeat to continue sifting down the child
        root = swap;
      }
    }
  }

  /**
   * Compares two posts to determine their ordering.
   * 
   * @param ref1 Reference to post1
   * @param ref2 Reference to post2
   * @return True if post represented by ref1 should be displayed before
   *         the post represented by ref2. False otherwise.
   */
  private boolean isBefore(final PostRef ref1, final PostRef ref2) {
    Post post1 = ref1.getPost();
    Post post2 = ref2.getPost();

    // First compare the posts based on their creation time
    int result = post1.getCreationTime().compareTo(post2.getCreationTime());

    if (result == 0) {
      // If posts were created at the same time, compare ids so that their
      // natural ordering is preserved
      result = Long.compare(post1.getId(), post2.getId());
    }

    return result < 0;
  }
}
