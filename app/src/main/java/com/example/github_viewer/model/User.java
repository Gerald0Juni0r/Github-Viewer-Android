package com.example.github_viewer.model;

public class User {
    private String login;
    private String avatar_url;
    private String name;
    private Integer public_repos;

    // Constructor User()
    public User(String login, String avatar_url, String name, Integer public_repos) {
        this.login = login;
        this.avatar_url = avatar_url;
        this.name = name;
        this.public_repos = public_repos;
    }

    // Getters e Setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(Integer public_repos) {
        this.public_repos = public_repos;
    }
}