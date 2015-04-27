package org.krzogr.chirper.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.krzogr.chirper.service.Post;

/**
 * Unit test to verify the behaviour of WallPostIterator.
 */
public class WallPostIteratorTest {
  @Test
  public void testEmptyIteratorList() {
    WallPostIterator iter = new WallPostIterator(emptyList());
    assertEmpty(iter);
  }

  @Test
  public void testSingleEmptyIterator() {
    WallPostIterator iter = new WallPostIterator(emptyList());
    assertEmpty(iter);
  }

  @Test
  public void testMultipleEmptyIterators() {
    WallPostIterator iter = new WallPostIterator(asList(iterator(), iterator(),
        iterator()));
    assertEmpty(iter);
  }

  @Test
  public void testSingleIteratorOnePost() {
    WallPostIterator iter = new WallPostIterator(asList(iterator(post(1,
        "10:00:00"))));
    assertPostIds(iter, 1);
  }

  @Test
  public void testSingleIteratorTwoPosts() {
    WallPostIterator iter = new WallPostIterator(asList(iterator(
        post(1, "10:00:00"), post(2, "10:00:00"))));
    assertPostIds(iter, 1, 2);
  }

  @Test
  public void testAllCreationTimesEqualSortByPostId() {
    Iterator<Post> iterator1 = iterator(post(9, "10:00:00"),
        post(8, "10:00:00"));
    Iterator<Post> iterator2 = iterator(post(7, "10:00:00"),
        post(6, "10:00:00"), post(5, "10:00:00"));
    Iterator<Post> iterator3 = iterator(post(4, "10:00:00"),
        post(3, "10:00:00"), post(1, "10:00:00"));
    Iterator<Post> iterator4 = iterator(post(2, "10:00:00"));

    WallPostIterator iter = new WallPostIterator(asList(iterator1, iterator2,
        iterator3, iterator4));
    assertPostIds(iter, 9, 8, 7, 6, 5, 4, 3, 2, 1);
  }

  @Test
  public void testSortByCreationTime() {
    Iterator<Post> iterator1 = iterator(post(9, "10:00:32"),
        post(8, "10:00:27"));
    Iterator<Post> iterator2 = iterator(post(7, "10:00:33"),
        post(6, "10:00:31"), post(5, "10:00:28"));
    Iterator<Post> iterator3 = iterator(post(4, "10:00:34"),
        post(3, "10:00:30"), post(1, "10:00:29"));
    Iterator<Post> iterator4 = iterator(post(2, "10:00:35"));

    WallPostIterator iter = new WallPostIterator(asList(iterator1, iterator2,
        iterator3, iterator4));
    assertPostIds(iter, 2, 4, 7, 9, 6, 3, 1, 5, 8);
  }

  private void assertEmpty(WallPostIterator iter) {
    assertPostIds(iter, new long[] {});
  }

  private void assertPostIds(WallPostIterator iter, long... ids) {
    List<Long> expectedIds = new ArrayList<Long>(ids.length);
    for (int i = 0; i < ids.length; i++) {
      expectedIds.add(ids[i]);
    }

    List<Long> actualIds = new ArrayList<Long>();
    iter.forEachRemaining(i -> actualIds.add(i.getId()));

    assertEquals(expectedIds, actualIds);
  }

  private Iterator<Post> iterator(Post... posts) {
    return asList(posts).iterator();
  }

  private Post post(long id, String creationTimeStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalTime creationTime = LocalTime.parse(creationTimeStr, formatter);

    return new DummyPostImpl(id, "dummy", LocalDateTime.now()
        .with(creationTime));
  }
}
