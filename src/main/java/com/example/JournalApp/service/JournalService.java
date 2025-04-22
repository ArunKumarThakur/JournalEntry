package com.example.JournalApp.service;

import com.example.JournalApp.entity.JournalEntry;

import java.util.List;

public interface JournalService {
    void addJournal(JournalEntry journal);
    JournalEntry getJournalById(Long id);
    List<JournalEntry> getAllJournals();
    String getTitleById(Long id);
    String getContentById(Long id);

}
