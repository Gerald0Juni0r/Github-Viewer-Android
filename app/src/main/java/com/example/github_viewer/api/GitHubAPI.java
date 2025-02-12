package com.example.github_viewer.api;

import com.example.github_viewer.model.Repository;
import com.example.github_viewer.model.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubAPI {
    @GET("users/{username}")
    Call<User> getUserProfile(@Path("username") String username);

    @GET("users/{username}/repos")
    Call<List<Repository>> getUserRepositories(@Path("username") String username);

    @GET("repos/{owner}/{repo}/languages")
    Call<Map<String, Integer>> getRepositoryLanguages(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}