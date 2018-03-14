package org.shoppingservicev2.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserCrudTest {

    @Autowired
    UserCrud userCrud;

    @Before
    public void setUp() {
        UserDto userDto = new UserDto();
        userDto.setPassword("1234");
        userDto.setLogin("login1234");
        userCrud.createUser(userDto);
    }

    @Test
    public void shouldAddUser() {
        UserDto userDto = new UserDto();
        userDto.setLogin("login");
        userDto.setPassword("password");
        Response response = userCrud.createUser(userDto);

        Assert.assertTrue(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "User successfully created!");
    }

    @Test
    public void shouldNotAddUserWithTooShortLogin() {
        UserDto userDto = new UserDto();
        userDto.setLogin("log");
        userDto.setPassword("password");
        Response response = userCrud.createUser(userDto);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Login too short");
    }

    @Test
    public void shouldNotAddUserWithAlreadyExistingLogin() {
        UserDto userDto = new UserDto();
        userDto.setLogin("login");
        userDto.setPassword("password");
        userCrud.createUser(userDto);
        userDto.setPassword("password123");
        Response response = userCrud.createUser(userDto);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Login already exist, try with other one");
    }

    @Test
    public void shouldNotAddUserWithEmptyPassword() {
        UserDto userDto = new UserDto();
        userDto.setLogin("login");
        userDto.setPassword("");
        Response response = userCrud.createUser(userDto);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Password cannot be null");
    }

    @Test
    public void shouldNotAddUserWithNoLoginAndNoPassword() {
        UserDto userDto = new UserDto();
        userDto.setLogin("");
        userDto.setPassword("");
        Response response = userCrud.createUser(userDto);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Login too short");
    }

    @Test
    public void shouldSignIn() {
        UserDto userDto = new UserDto();
        userDto.setLogin("login1234");
        userDto.setPassword("1234");
        Response response = userCrud.signIn(userDto);

        Assert.assertTrue(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Login and password correct");
    }

    @Test
    public void shouldNotSignInWhenLoginIncorrect() {
        UserDto userDto = new UserDto();
        userDto.setLogin("login");
        userDto.setPassword("1234");
        Response response = userCrud.signIn(userDto);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Login or password incorrect");
    }

    @Test
    public void shouldNotSignInWhenPasswordIncorrect() {
        UserDto userDto = new UserDto();
        userDto.setLogin("login1234");
        userDto.setPassword("12345");
        Response response = userCrud.signIn(userDto);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Login or password incorrect");
    }

    @Test
    public void shouldNotSignInWhenEmptyLoginAndEmptyPasswordPassed(){
        UserDto userDto = new UserDto();
        userDto.setLogin("");
        userDto.setPassword("");
        Response response = userCrud.signIn(userDto);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Login or password incorrect");
    }

    @Test
    public void shouldFindUserId(){
        UserDto userDto = new UserDto();
        userDto.setLogin("login1234");
        userDto.setPassword("1234");
        Response response = userCrud.findUserId(userDto.getLogin());

        Assert.assertEquals(response.getMessage().length(), 1);
        Assert.assertEquals(response.getMessage(), "1");
    }

    @Test
    public void shouldThrowNoSuchUserExceptionWhenQueryForUserThatDoesNotExist(){
        UserDto userDto = new UserDto();
        userDto.setLogin("login");
        userDto.setPassword("1234");
        Response response = userCrud.findUserId(userDto.getLogin());

        Assert.assertEquals(response.getMessage(), "login does not exist");
    }
}
