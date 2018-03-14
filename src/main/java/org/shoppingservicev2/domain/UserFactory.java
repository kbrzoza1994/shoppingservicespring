package org.shoppingservicev2.domain;

import org.shoppingservicev2.service.UserDto;

public class UserFactory {

    public User createFrom(UserDto userDto){ return userBuilder(userDto);}

    private User userBuilder(UserDto userDto) {
        return new User.UserBuilder()
                .withLogin(userDto.getLogin())
                .withPassword(userDto.getPassword())
                .build();
    }
}
