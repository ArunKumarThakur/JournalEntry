package com.example.JournalApp.service;

import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.entity.User;
import com.example.JournalApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImple implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean saveUser(User user) {

        String userId = user.getId();
        if(getById(userId) == null) {
            userRepo.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User getById(String id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public boolean changePassword(String id, String newPassword) {
        User user = getById(id);
        if (user == null) return false;

        user.setPassword(newPassword);
        userRepo.save(user); // ✅ Important: persist the change
        return true;
    }

    @Override
    public boolean changeUserName(String id, String newUsername) {
        User user = getById(id);
        if (user == null) return false;

        user.setUsername(newUsername);
        userRepo.save(user); // ✅ Important
        return true;
    }

    @Override
    public List<JournalEntry> getEntriesById(String id) {
        User user = getById(id);
        if (user == null || user.getJournalEntries() == null) {
            return Collections.emptyList();
        }
        return user.getJournalEntries();
    }
}
