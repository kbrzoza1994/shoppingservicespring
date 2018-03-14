package org.shoppingservicev2.service;

public class UserDto {
    private static final String NO_VALUE = "";

    private String login = NO_VALUE;
    private String password = NO_VALUE;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
