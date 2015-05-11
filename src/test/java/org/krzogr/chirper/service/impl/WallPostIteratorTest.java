package org.krzogr.chirper.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
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
    WallPostIterator iter = new WallPostIterator(asList(posts(), posts(),
        posts()));
    assertEmpty(iter);
  }

  @Test
  public void testSingleIteratorOnePost() {
    WallPostIterator iter = new WallPostIterator(asList(posts(post(1,
        "10:00:00"))));
    assertPostIds(iter, 1);
  }

  @Test
  public void testSingleIteratorTwoPosts() {
    WallPostIterator iter = new WallPostIterator(asList(posts(
        post(1, "10:00:00"), post(2, "10:00:00"))));
    assertPostIds(iter, 1, 2);
  }

  @Test
  public void testSingleIteratorThreePosts() {
    WallPostIterator iter = new WallPostIterator(asList(posts(
        post(1, "10:00:00"), post(2, "10:00:00"), post(3, "10:00:00"))));
    assertPostIds(iter, 1, 2, 3);
  }
  
  @Test
  public void testAllCreationTimesEqualSortByPostId() {
    Iterable<Post> iterable1 = posts(post(9, "10:00:00"),
        post(8, "10:00:00"));
    Iterable<Post> iterable2 = posts(post(7, "10:00:00"),
        post(6, "10:00:00"), post(5, "10:00:00"));
    Iterable<Post> iterable3 = posts(post(4, "10:00:00"),
        post(3, "10:00:00"), post(1, "10:00:00"));
    Iterable<Post> iterable4 = posts(post(2, "10:00:00"));

    WallPostIterator iter = new WallPostIterator(asList(iterable1, iterable2,
        iterable3, iterable4));
    assertPostIds(iter, 9, 8, 7, 6, 5, 4, 3, 2, 1);
  }

  @Test
  public void testSortByCreationTime() {
    Iterable<Post> iterable1 = posts(post(9, "10:00:32"),
        post(8, "10:00:27"));
    Iterable<Post> iterable2 = posts(post(7, "10:00:33"),
        post(6, "10:00:31"), post(5, "10:00:28"));
    Iterable<Post> iterable3 = posts(post(4, "10:00:34"),
        post(3, "10:00:30"), post(1, "10:00:29"));
    Iterable<Post> iterable4 = posts(post(2, "10:00:35"));

    WallPostIterator iter = new WallPostIterator(asList(iterable1, iterable2,
        iterable3, iterable4));
    assertPostIds(iter, 2, 4, 7, 9, 6, 3, 1, 5, 8);
  }

  @Test
  public void testFourIterablesOneEmpty() {
    Iterable<Post> iterable1 = posts(post(9, "10:00:32"),
        post(8, "10:00:27"));
    Iterable<Post> iterable2 = posts();
    Iterable<Post> iterable3 = posts(post(4, "10:00:34"),
        post(3, "10:00:30"), post(1, "10:00:29"));
    Iterable<Post> iterable4 = posts(post(2, "10:00:35"));

    WallPostIterator iter = new WallPostIterator(asList(iterable1, iterable2,
        iterable3, iterable4));
    assertPostIds(iter, 2, 4, 9, 3, 1, 8);
  }
  
  @Test
  public void testFourIterablesTwoEmpty() {
    Iterable<Post> iterable1 = posts();
    Iterable<Post> iterable2 = posts();
    Iterable<Post> iterable3 = posts(post(4, "10:00:34"),
        post(3, "10:00:30"), post(1, "10:00:29"));
    Iterable<Post> iterable4 = posts(post(2, "10:00:35"));

    WallPostIterator iter = new WallPostIterator(asList(iterable1, iterable2,
        iterable3, iterable4));
    assertPostIds(iter, 2, 4, 3, 1);
  }
  
  @Test
  public void testFourIterablesThreeEmpty() {
    Iterable<Post> iterable1 = posts();
    Iterable<Post> iterable2 = posts();
    Iterable<Post> iterable3 = posts(post(4, "10:00:34"),
        post(3, "10:00:30"), post(1, "10:00:29"));
    Iterable<Post> iterable4 = posts();

    WallPostIterator iter = new WallPostIterator(asList(iterable1, iterable2,
        iterable3, iterable4));
    assertPostIds(iter, 4, 3, 1);
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

  private Iterable<Post> posts(Post... posts) {
    return asList(posts);
  }

  private Post post(long id, String creationTimeStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalTime creationTime = LocalTime.parse(creationTimeStr, formatter);

    return new DummyPostImpl(id, "dummy", LocalDateTime.now()
        .with(creationTime));
  }
}
