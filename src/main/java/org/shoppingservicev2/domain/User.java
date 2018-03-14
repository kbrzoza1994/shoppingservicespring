package org.shoppingservicev2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String login;
    private String password;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public User() {
    }

    public User(UserBuilder userBuilder) {
        this.login = userBuilder.login;
        this.password = userBuilder.password;
    }


    static class UserBuilder {
        private String login;
        private String password;

        UserBuilder withLogin(String login) {
            this.login = login;
            return this;
        }

        UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        User build() {
            return new User(this);
        }
    }
}
