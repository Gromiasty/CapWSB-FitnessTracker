package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @GetMapping("/simple")
    public List<UserSimpleDto> getAllBasicInformationAboutUsers(){
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toSimpleDto)
                          .toList();
    }

    @GetMapping("/email")
    public List<UserEmailMapper> getUsersByEmailCases(@RequestParam String email) {
        return userService.findUsersByEmail(email)
        .stream()
        .map(userMapper::userToEmail)
        .toList();
    }

   /*   @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId)
                          .map(userMapper::toDto)
                          .orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " not found"));
    } */

    @GetMapping("/{value}")
    public UserDto getUser(@PathVariable String value) {
        if (userService.getUserByEmail(value).isPresent()) {
                return userService.getUserByEmail(value)
                .map(userMapper::toDto)
                .orElseThrow(RuntimeException::new);
            } else {
        return userService.getUser(Long.parseLong(value))
                          .map(userMapper::toDto)
                          .orElseThrow(RuntimeException::new/*() -> new IllegalArgumentException("User with ID: " + userId + " not found")*/);
            }
    }

    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {

        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");
        User user = userMapper.toEntity(userDto);

        // TODO: saveUser with Service and return User
         return userService.createUser(user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
            userService.deleteUser(userId);
    }

    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return userService.getOlderThan(time)
        .stream()
        .map(userMapper::toDto)
        .toList();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User chooseUser = userService.getUser(id).orElseThrow(() -> new IllegalArgumentException("User " + id + "not found"));
        User updateUserData = userMapper.updateChosenUser(chooseUser, userDto);

        return userService.updateUser(updateUserData);
    }

}