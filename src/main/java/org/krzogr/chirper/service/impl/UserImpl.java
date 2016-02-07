package org.krzogr.chirper.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.krzogr.chirper.service.Post;
import org.krzogr.chirper.service.User;

/** Default, in-memory and <b>single threaded</b> implementation of User. */
public final class UserImpl implements User {
  private final long id;
  private final String userName;
  private final LinkedList<Post> posts;
  private final HashSet<User> usersToFollow;
  private final UserManagerImpl userManager;

  public UserImpl(final long id, final String userName, final UserManagerImpl userManager) {
    Objects.requireNonNull(userName);
    Objects.requireNonNull(userManager);

    this.id = id;
    this.userName = userName;
    this.userManager = userManager;
    this.posts = new LinkedList<Post>();
    this.usersToFollow = new HashSet<User>();
  }

  @Override
  public long getId() {
    return id;
  }

  @Override
  public String getUserName() {
    return userName;
  }

  @Override
  public Post addPost(final String text) {
    Objects.requireNonNull(text);

    Post post = new PostImpl(userManager.nextPostId(), text, this, LocalDateTime.now(userManager.getClock()));
    posts.addFirst(post);

    return post;
  }

  @Override
  public void followUser(final User user) {
    Objects.requireNonNull(user);
    if (user == this) {
      throw new IllegalArgumentException("User cannot follow himself: " + user.getUserName());
    }

    usersToFollow.add(user);
  }

  @Override
  public Iterable<Post> getTimeline() {
    return posts;
  }

  @Override
  public Iterable<Post> getWall() {
    List<Iterable<Post>> iterables = new ArrayList<Iterable<Post>>(usersToFollow.size() + 1);

    iterables.add(posts);
    usersToFollow.forEach(user -> iterables.add(user.getTimeline()));

    return new WallPostIterable(iterables);
  }
}
