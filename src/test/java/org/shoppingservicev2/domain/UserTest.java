package org.shoppingservicev2.domain;

import org.junit.Assert;
import org.junit.Test;
import org.shoppingservicev2.service.UserDto;

public class UserTest {

    @Test
    public void shouldCreateUser() {
        UserDto userDto = new UserDto();
        userDto.setLogin("login");
        userDto.setPassword("password");

        User user = new UserFactory().createFrom(userDto);

        Assert.assertEquals(user.getLogin(), userDto.getLogin());
        Assert.assertEquals(user.getPassword(), userDto.getPassword());
    }
}
