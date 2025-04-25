package com.example.JournalApp.controller;

import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.entity.User;
import com.example.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Create a new user
    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        boolean success = userService.saveUser(user);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).body("User saved with ID: " + user.getId());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
    }

    // ✅ Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        User user = userService.getById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok(user);
    }

    // ✅ Change password
    @PutMapping("/{id}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable String id, @RequestParam String newPassword) {
        boolean success = userService.changePassword(id, newPassword);
        if (success) {
            return ResponseEntity.ok("Password changed successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
    }

    // ✅ Change username
    @PutMapping("/{id}/change-username")
    public ResponseEntity<String> changeUsername(@PathVariable String id, @RequestParam String newUsername) {
        boolean success = userService.changeUserName(id, newUsername);
        if (success) {
            return ResponseEntity.ok("Username changed successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
    }

    // ✅ Get all journal entries by user
    @GetMapping("/{id}/journals")
    public ResponseEntity<?> getAllJournalsByUser(@PathVariable String id) {
        List<JournalEntry> entries = userService.getEntriesById(id);
        if (entries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No entries found or invalid user ID");
        }
        return ResponseEntity.ok(entries);
    }
}
