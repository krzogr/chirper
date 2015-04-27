package org.krzogr.chirper.service;

import java.time.LocalDateTime;

/**
 * Represents a user in Chirper application.
 * <p>
 * Each user has unique user name and is identified by unique identifier.
 * </p>
 */
public interface User {
	/**
	 * Returns the unique identifier of the user.
	 * 
	 * @return Unique identifier of the user.
	 */
	long getId();
	
	/**
	 * Returns unique user name.
	 * 
	 * @return Unique user name.
	 */
	String getUserName();
	
	/**
	 * Adds new post to the list of posts sent by this user.
	 * 
	 * @param postText Text of the post (cannot be NULL).
	 * @param creationTime Post creation time (cannot be NULL).
	 * @return The newly added post.
	 * @throws NullPointerException if any parameter is NULL.
	 */
	Post addPost(String postText, LocalDateTime creationTime);
	
	/**
	 * Makes this user follow another user.
	 * <p>
	 * Wall posts will include all posts sent by all followed users.
	 * </p>
	 *  
	 * @param userToFollow User to follow.
	 */
	void followUser(User userToFollow);
	
	/**
	 * Returns this user's posts in descending order based on creation time.
	 * 
	 * @return Posts sent by this user.
	 */
	Iterable<Post> getTimeline();
	
	/**
	 * Returns all posts sent by this user and all followed users.
	 * <p>
	 * Posts are returned in descending order based on creation time.
	 * </p>
	 * 
	 * @return All posts sent by this user and all followed users.
	 */
	Iterable<Post> getWall();
}
