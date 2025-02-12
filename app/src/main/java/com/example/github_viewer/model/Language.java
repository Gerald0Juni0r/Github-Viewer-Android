package com.example.github_viewer.model;

public class Language {
    private String language;
    private Integer bytes;

    // Constructor, getters, and setters
    public Language(String language, Integer bytes) {
        this.language = language;
        this.bytes = bytes;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getBytes() {
        return bytes;
    }

    public void setBytes(Integer bytes) {
        this.bytes = bytes;
    }
}