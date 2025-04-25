package com.example.JournalApp.service;

import com.example.JournalApp.entity.JournalEntry;
import java.util.List;

public interface JournalService {
    boolean addJournal(JournalEntry journal);
    JournalEntry getJournalById(String id);
    List<JournalEntry> getAllJournals();
    String getTitleById(String id);
    String getContentById(String id);
    boolean deleteById(String id);

    // ✅ Optional additions
    boolean updateJournal(String id, JournalEntry updatedJournal);
    List<JournalEntry> getJournalsByUserId(String userId);
    List<JournalEntry> searchByKeyword(String keyword);
}
