package com.example.JournalApp.controller;

import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.service.JournalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/journal")
public class JournalController {

    @Autowired
    private JournalServiceImpl journalService;

    @PostMapping("/save")
    public ResponseEntity<JournalEntry> saveJournal(@RequestBody JournalEntry journalEntry) {
        journalService.addJournal(journalEntry);
        return ResponseEntity.status(HttpStatus.OK).body(journalEntry);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getTitleById(@PathVariable Long id) {
        String title = journalService.getTitleById(id);

        if(title != null) {
            return ResponseEntity.status(HttpStatus.OK).body(title);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }

}
