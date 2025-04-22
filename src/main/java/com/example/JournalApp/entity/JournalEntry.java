package com.example.JournalApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity(name = "journal")
public class JournalEntry {

    @Id
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    public JournalEntry(){};

    public JournalEntry(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
