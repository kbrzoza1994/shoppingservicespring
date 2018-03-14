package org.shoppingservicev2.controller;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldCreateNewUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/create?login=login&password=password").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("User successfully created!")));
    }

    @Test
    public void shouldNotCreateUserWhenTooShortLoginPassed() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/create?login=log&password=password").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("Login too short")));
    }

    @Test
    public void shouldNotCreateUserWhenTryToAddAlreadyExistingLogin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/create?login=login&password=password").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/users/create?login=login&password=password123").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("Login already exist, try with other one")));
    }

    @Test
    public void shouldNotCreateUserWhenEmptyPasswordPassed() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/create?login=login").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("Password cannot be null")));
    }

    @Test
    public void shouldNotAddUserWithNoLoginAndNoPassword() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/create").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("Login too short")));
    }

    @Test
    public void shouldSignIn() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/create?login=login&password=password").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("User successfully created!")));
        mvc.perform(MockMvcRequestBuilders.get("/users/login?login=login&password=password").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("Login and password correct")));
    }

    @Test
    public void shouldNotSignInWhenLoginIsIncorrect() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/create?login=login&password=password").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("User successfully created!")));
        mvc.perform(MockMvcRequestBuilders.get("/users/login?login=login81&password=password").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("Login or password incorrect")));
    }

    @Test
    public void shouldNotSignInWhenPasswordIsIncorrect() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/create?login=login&password=password").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("User successfully created!")));
        mvc.perform(MockMvcRequestBuilders.get("/users/login?login=login&password=pass").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("Login or password incorrect")));

    }

    @Test
    public void shouldNotSignInWhenNoParameterPassed() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/create?login=login&password=password").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("User successfully created!")));
        mvc.perform(MockMvcRequestBuilders.get("/users/login").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("Login or password incorrect")));
    }

    @Test
    public void shouldFindUserId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/create?login=login&password=password").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("User successfully created!")));
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/users/find?login=login").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assert.assertEquals(response.getContentAsString(), "1");
    }

    @Test
    public void  shouldNotFindUserThatDoesNotExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/create?login=login&password=password").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("User successfully created!")));
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/users/find?login=asadasa").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assert.assertEquals(response.getContentAsString(), "asadasa does not exist");
    }
}
