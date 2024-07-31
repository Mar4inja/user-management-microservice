package de.ait.usermanagment.service.implementation;

import de.ait.usermanagment.exceptions.RequiredDataException;
import de.ait.usermanagment.exceptions.UserAlreadyExistsException;
import de.ait.usermanagment.exceptions.UserIsNotExistsException;
import de.ait.usermanagment.model.User;
import de.ait.usermanagment.repository.RoleRepository;
import de.ait.usermanagment.repository.UserRepository;
import de.ait.usermanagment.service.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceInterface.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User createUser(User user) {
        user.setId(null);

        if (user.getFirstName() == null || user.getFirstName().isEmpty()
                || user.getLastName() == null || user.getLastName().isEmpty()
                || user.getBirthDate() == null
                || user.getEmail() == null || user.getEmail().isEmpty()
                || user.getPassword() == null || user.getPassword().isEmpty()
                || user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty()
                || user.getAddress() == null) {
            throw new RequiredDataException("All fields is required");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistsException("User with email: " + user.getEmail() + " already exists");
        }

        user.setRoles(Collections.singleton(roleRepository.findByTitle("ROLE_USER")));
        user.setRegistrationDate(LocalDateTime.now());
        user.setActive(true);
        User savedUser = userRepository.save(user);
        logger.info("User created: " + savedUser);
        return savedUser;
    }

    @Override
    public User getUser(long id) {

        if (userRepository.findById(id) == null) {
            throw new UserIsNotExistsException("User is not exist!");
        } else {
            return userRepository.findById(id).get();
        }
    }

    @Override
    public User updateUser(User updatedUser) {
        User currentUser = findByEmail(updatedUser.getEmail());
        if (currentUser == null) {
            throw new UserIsNotExistsException("User with email " + updatedUser.getEmail() + " does not exist!");
        }

        Long id = currentUser.getId();

        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            if (updatedUser.getFirstName() != null) {
                existingUser.setFirstName(updatedUser.getFirstName());
            }
            if (updatedUser.getLastName() != null) {
                existingUser.setLastName(updatedUser.getLastName());
            }
            if (updatedUser.getBirthDate() != null) {
                existingUser.setBirthDate(updatedUser.getBirthDate());
            }
            if (updatedUser.getAddress() != null) {
                existingUser.setAddress(updatedUser.getAddress());
            }

            User updatedUserInDb = userRepository.save(existingUser);
            logger.info("User updated: " + updatedUserInDb);
            return updatedUserInDb;
        } else {
            throw new UserIsNotExistsException("User with ID " + id + " does not exist!");
        }
    }


    @Override
    public void deleteUser(long id) {

        if (userRepository.findById(id) == null) {
            throw new UserIsNotExistsException("User with ID " + id + " does not exist!");
        } else {
            userRepository.deleteById(id);
        }
    }

    @Override
    public User findByEmail(String username) {
        logger.info("Searching for user with email: {}", username);
        User user = userRepository.findByEmail(username);
        if (user == null) {
            logger.warn("User with email {} not found", username);
        } else {
            logger.info("User with email {} found", username);
        }
        return user;
    }
}
