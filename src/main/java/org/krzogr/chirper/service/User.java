package org.krzogr.chirper.service;

/** Represents a user in Chirper application.
 * <p>
 * Each user has unique user name and is identified by unique identifier.
 * </p> */
public interface User {
  /** Returns the unique identifier of the user.
   * 
   * @return Unique identifier of the user. */
  long getId();

  /** Returns unique user name.
   * 
   * @return Unique user name. */
  String getUserName();

  /** Adds new post to the list of posts sent by this user.
   * 
   * @param postText Text of the post (cannot be NULL).
   * @return The newly added post.
   * @throws NullPointerException if any parameter is NULL. */
  Post addPost(String postText);

  /** Makes this user follow another user.
   * <p>
   * Wall posts will include all posts sent by all followed users.
   * </p>
   * 
   * @param userToFollow User to follow. */
  void followUser(User userToFollow);

  /** Returns this user's posts.
   * <p>
   * Posts are returned in ascending order based on post age (i.e. the most
   * recently added post is returned first).
   * </p>
   * 
   * @return Posts sent by this user. */
  Iterable<Post> getTimeline();

  /** Returns all posts sent by this user and all followed users.
   * <p>
   * All posts are returned in ascending order based on post age (i.e. the most
   * recently added post is returned first).
   * </p>
   * 
   * @return All posts sent by this user and all followed users. */
  Iterable<Post> getWall();
}
