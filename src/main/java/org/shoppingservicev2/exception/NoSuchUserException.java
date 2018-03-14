package org.shoppingservicev2.exception;

public class NoSuchUserException extends RuntimeException {
    private final String message = "USER DOESN'T EXIST";
    private String login;

    public NoSuchUserException(String login) {
        this.login = login;
    }

    @Override
    public String getMessage() {
        return login + " - " + message;
    }
}
