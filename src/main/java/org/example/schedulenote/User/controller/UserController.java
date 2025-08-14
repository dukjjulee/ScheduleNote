package org.example.schedulenote.User.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulenote.User.dto.UserResponse;
import org.example.schedulenote.User.dto.UserSaveRequest;
import org.example.schedulenote.User.dto.UserSaveResponse;
import org.example.schedulenote.User.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserSaveResponse> saveUsers(
            @RequestBody UserSaveRequest request
    ) {
        return ResponseEntity.ok(userService.save(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> users = userService.findUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUsers(
            @PathVariable long scheduleId,
            long userId) {
        return ResponseEntity.ok(userService.findUser(userId));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponse> UpdateUser(
            @PathVariable long userId,
            @RequestBody UserSaveRequest request
    ) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    @DeleteMapping("/users/{userId}")
    public void deletUser(
            @PathVariable long userId
            @RequestParam String password
    ) {
        userService.deleteUser(userId, password);
    }
}
