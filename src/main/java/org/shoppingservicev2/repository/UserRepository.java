package org.shoppingservicev2.repository;

import org.shoppingservicev2.domain.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {

    User findFirstByLogin(String login);

    User findFirstByLoginAndPassword(String login, String password);
}
