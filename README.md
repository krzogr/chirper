# chirper
Console java application which implements simple chat-like command interface.

## Commands
`<user> -> <message>` 
> Posts a new message as the specified user.

`<user>`
> Shows all posts of the specified user

`<user1> follows <user2>`
> Makes user1 the follower of user2 (user1's wall will include user2's posts) 

`<user> wall`
> Shows wall posts of the specified user

## Requirements
- JRE 1.8
- Maven 3.3.1

## Installation
```
mvn install
java -jar target/chirper-1.0.jar
```
