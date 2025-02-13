package com.example.github_viewer;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.github_viewer.api.GitHubAPI;
import com.example.github_viewer.api.RetrofitClient;
import com.example.github_viewer.model.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText edtUsuario;
    private Button btnBuscar;
    private GitHubAPI gitHubAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario = findViewById(R.id.edtUsuario);
        btnBuscar = findViewById(R.id.btnBuscar);

        gitHubAPI = RetrofitClient.getClient().create(GitHubAPI.class);

        btnBuscar.setOnClickListener(v -> buscarUsuario());
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Erro")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {}) // Close dialog on OK
                .setCancelable(false)
                .show();
    }

    private void buscarUsuario() {
        String username = edtUsuario.getText().toString().trim();

        if (username.isEmpty()) {
            showErrorDialog("Por favor, insira um nome de usuário");
            return;
        }

        gitHubAPI.getUserProfile(username).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();

                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("login", user.getLogin());
                    intent.putExtra("avatar_url", user.getAvatar_url());
                    intent.putExtra("name", user.getName());
                    intent.putExtra("public_repos", user.getPublic_repos());
                    startActivity(intent); // Start the activity!
                } else {
                    int statusCode = response.code();
                    String errorMessage = "User not found. Status Code: " + statusCode;
                    if (response.errorBody() != null) {
                        try {
                            errorMessage += " - " + response.errorBody().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.e("MainActivity", errorMessage);
                    showErrorDialog(errorMessage);

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                String errorMessage = "Network error. Check your connection.";
                Log.e("MainActivity", "Request failed: " + t.getMessage());
                showErrorDialog(errorMessage);
            }
        });
    }
}