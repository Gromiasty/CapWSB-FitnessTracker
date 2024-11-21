package com.capgemini.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    Optional<User> getUser(Long id);

    User updateUser(Long id, User user);

    List<User> findAllUsers();

    void deleteUser(Long id);

    List<User> findUsersByEmailFragment(String emailFragment);

    List<User> findUsersOlderThan(LocalDate cutoffDate);
}