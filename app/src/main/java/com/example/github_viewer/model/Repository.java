package com.example.github_viewer.model;

public class Repository {
    private String name;
    private String language;

    // Constructor Repository()
    public Repository(String name, String language) {
        this.name = name;
        this.language = language;
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}