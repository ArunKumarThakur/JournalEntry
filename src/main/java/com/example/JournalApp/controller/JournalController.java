package com.example.JournalApp.controller;

import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.service.JournalService;
import com.example.JournalApp.service.UserService;
import com.example.JournalApp.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    // ✅ Save new journal
    @PostMapping("/save")
    public ResponseEntity<String> saveJournal(@RequestBody JournalEntry journalEntry) {
        if (journalEntry.getUser() == null || journalEntry.getUser().getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User ID is required");
        }

        User user = userService.getById(journalEntry.getUser().getId());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User ID");
        }

        journalEntry.setUser(user);
        boolean success = journalService.addJournal(journalEntry);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).body("Journal saved with ID: " + journalEntry.getId());
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Journal with this ID already exists");
        }
    }

    // ✅ Get full journal entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getJournalById(@PathVariable String id) {
        JournalEntry journal = journalService.getJournalById(id);
        if (journal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Journal not found");
        }
        return ResponseEntity.ok(journal);
    }

    // ✅ Get all journal entries
    @GetMapping("/all")
    public ResponseEntity<List<JournalEntry>> getAllJournals() {
        return ResponseEntity.ok(journalService.getAllJournals());
    }

    // ✅ Get only title by ID
    @GetMapping("/{id}/title")
    public ResponseEntity<String> getTitleById(@PathVariable String id) {
        String title = journalService.getTitleById(id);
        if (title != null) {
            return ResponseEntity.ok(title);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Title not found");
    }

    // ✅ Get only content by ID
    @GetMapping("/{id}/content")
    public ResponseEntity<String> getContentById(@PathVariable String id) {
        String content = journalService.getContentById(id);
        if (content != null) {
            return ResponseEntity.ok(content);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Content not found");
    }

    // ✅ Delete journal by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        boolean success = journalService.deleteById(id);
        if (success) {
            return ResponseEntity.ok("Journal deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Journal not found");
    }
}
