package com.example.JournalApp.service;

import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.repo.JournalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService{

    @Autowired
    private JournalRepo journalRepo;

    public void addJournal(JournalEntry journal) {
        journalRepo.save(journal);
    }
    public JournalEntry getJournalById(Long id) {
        Optional<JournalEntry> journalEntry = journalRepo.findById(id);
        return journalEntry.orElse(null);
    }

    public List<JournalEntry> getAllJournals(){
        return journalRepo.findAll();
    }
    public String getTitleById(Long id){
        JournalEntry journalEntry = getJournalById(id);
        return journalEntry == null ? null : journalEntry.getTitle();
    }

    public String getContentById(Long id){
        JournalEntry journalEntry = getJournalById(id);
        return journalEntry == null ? null: journalEntry.getContent();
    }
}
