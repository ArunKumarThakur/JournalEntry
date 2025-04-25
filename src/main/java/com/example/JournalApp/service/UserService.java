package com.example.JournalApp.service;

import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.entity.User;

import java.util.List;

public interface UserService {
    boolean saveUser(User user);
    User getById(String id);
    boolean changePassword(String id, String now);
    boolean changeUserName(String id, String name);
    List<JournalEntry> getEntriesById(String id);
}
