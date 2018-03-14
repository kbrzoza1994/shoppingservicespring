package org.shoppingservicev2.service;

import org.shoppingservicev2.domain.User;
import org.shoppingservicev2.domain.UserFactory;
import org.shoppingservicev2.exception.NoSuchUserException;
import org.shoppingservicev2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCrud {

    @Autowired
    private UserRepository userRepository;


    public Response createUser(UserDto userDto) {
        if (userDto.getLogin().length() <= 3)
            return Response.aFailureResponse("Login too short");
        if (userRepository.findFirstByLogin(userDto.getLogin()) != null)
            return Response.aFailureResponse("Login already exist, try with other one");
        if (userDto.getPassword().equals(""))
            return Response.aFailureResponse("Password cannot be null");

        userRepository.save(new UserFactory().createFrom(userDto));
        return Response.aSuccessfulResponseWith("User successfully created!");
    }

    public Response signIn(UserDto userDto) {
        if (userDto.getLogin().length() <= 3 || userDto.getPassword().equals(""))
            return Response.aFailureResponse("Login or password incorrect");
        else if (userRepository.findFirstByLoginAndPassword(userDto.getLogin(), userDto.getPassword()) == null)
            return Response.aFailureResponse("Login or password incorrect");

        return Response.aSuccessfulResponseWith("Login and password correct");
    }

    public Response findUserId(String login) {
        if (login.isEmpty())
            return Response.aFailureResponse(login + " does not exist");
        User user = userRepository.findFirstByLogin(login);

        if (user == null)
            return Response.aFailureResponse(login + " does not exist");
        else
            return Response.aSuccessfulResponseWith(String.valueOf(user.getId()));
    }
}
