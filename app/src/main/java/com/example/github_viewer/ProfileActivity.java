package com.example.github_viewer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.github_viewer.api.GitHubAPI;
import com.example.github_viewer.api.RetrofitClient;
import com.example.github_viewer.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private ImageView imgProfile, btnVoltar;
    private TextView txtUsername, txtReposCount;
    // private ImageButton btnVoltar;
    private RecyclerView recyclerViewRepos;
    private RepositoryAdapter adapter;
    private GitHubAPI gitHubAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imgProfile = findViewById(R.id.imgProfile);
        txtUsername = findViewById(R.id.title_template);
        txtReposCount = findViewById(R.id.txtReposCount);
        recyclerViewRepos = findViewById(R.id.recyclerViewRepos);
        btnVoltar = findViewById(R.id.btnVoltar);

        recyclerViewRepos.setLayoutManager(new LinearLayoutManager(this));

        gitHubAPI = RetrofitClient.getClient().create(GitHubAPI.class);

        Intent intent = getIntent();
        String login = intent.getStringExtra("login");
        String avatarUrl = intent.getStringExtra("avatar_url");
        String name = intent.getStringExtra("name");
        int publicRepos = intent.getIntExtra("public_repos", 0);

        txtUsername.setText(name != null ? name : login);
        txtReposCount.setText("Public Repositories: " + publicRepos);

        Glide.with(this).load(avatarUrl).into(imgProfile);

        carregarRepositorios(login);

        btnVoltar.setOnClickListener(v -> finish());
    }

    private void carregarRepositorios(String username) {
        gitHubAPI.getUserRepositories(username).enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new RepositoryAdapter(response.body());
                    recyclerViewRepos.setAdapter(adapter);
                } else {
                    txtReposCount.setText("Nenhum repositório encontrado.");
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                txtReposCount.setText("Erro ao carregar repositórios.");
            }
        });
    }
}
