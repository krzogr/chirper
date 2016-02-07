package org.krzogr.chirper.service;

/** Represents central user manager in Chirper application. */
public interface UserManager {
  User getOrCreateUser(String userName);
}
