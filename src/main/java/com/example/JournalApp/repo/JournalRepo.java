package com.example.JournalApp.repo;

import com.example.JournalApp.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepo extends JpaRepository<JournalEntry, String> {
}
