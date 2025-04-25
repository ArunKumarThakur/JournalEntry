package com.example.JournalApp.service;

import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.repo.JournalRepo;
import com.example.JournalApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepo journalRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean addJournal(JournalEntry journal) {
        if (getJournalById(journal.getId()) == null) {
            journal.setDate(LocalDateTime.now());
            journalRepo.save(journal);
            return true;
        }
        return false;
    }

    @Override
    public JournalEntry getJournalById(String id) {
        Optional<JournalEntry> journalEntry = journalRepo.findById(id);
        return journalEntry.orElse(null);
    }

    @Override
    public List<JournalEntry> getAllJournals() {
        return journalRepo.findAll();
    }

    @Override
    public String getTitleById(String id) {
        JournalEntry journalEntry = getJournalById(id);
        return journalEntry == null ? null : journalEntry.getTitle();
    }

    @Override
    public String getContentById(String id) {
        JournalEntry journalEntry = getJournalById(id);
        return journalEntry == null ? null : journalEntry.getContent();
    }

    @Override
    public boolean deleteById(String id) {
        JournalEntry journalEntry = getJournalById(id);
        if (journalEntry == null) return false;

        journalRepo.deleteById(id);
        return true;
    }

    // ✅ Optional: Update Journal
    @Override
    public boolean updateJournal(String id, JournalEntry updatedJournal) {
        JournalEntry existing = getJournalById(id);
        if (existing == null) return false;

        existing.setTitle(updatedJournal.getTitle());
        existing.setContent(updatedJournal.getContent());
        existing.setDate(LocalDateTime.now());

        journalRepo.save(existing);
        return true;
    }

    // ✅ Optional: Get Journals by User ID
    @Override
    public List<JournalEntry> getJournalsByUserId(String userId) {
        return journalRepo.findAll()
                .stream()
                .filter(j -> j.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    // ✅ Optional: Search by Keyword
    @Override
    public List<JournalEntry> searchByKeyword(String keyword) {
        return journalRepo.findAll()
                .stream()
                .filter(j -> j.getTitle().toLowerCase().contains(keyword.toLowerCase())
                        || j.getContent().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
