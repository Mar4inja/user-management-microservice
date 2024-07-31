package de.ait.usermanagment.service;

import de.ait.usermanagment.model.User;

public interface UserServiceInterface {

    User createUser(User user);

    User getUser(long id);

    User updateUser(User updatedUser);

    void deleteUser(long id);

    User findByEmail(String username);
}
