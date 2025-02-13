package com.example.github_viewer;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.github_viewer.api.GitHubAPI;
import com.example.github_viewer.api.RetrofitClient;
import com.example.github_viewer.model.User;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Erro")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    // Voltar para a tela inicial (MainActivity)
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                })
                .setCancelable(false); // Impede que o usuário feche sem clicar em "OK"

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void buscarUsuario() {
        String username = edtUsuario.getText().toString().trim();

        if (username.isEmpty()) {
            showErrorDialog("Por favor, insira um nome de usuário"); // Exibindo mensagem de erro
            return;
        }

        gitHubAPI.getUserProfile(username).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();

                    // Passando dados para a tela de perfil
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("login", user.getLogin());
                    intent.putExtra("avatar_url", user.getAvatar_url());
                    intent.putExtra("name", user.getName());
                    intent.putExtra("public_repos", user.getPublic_repos());
                    startActivity(intent);
                } else {
                    showErrorDialog("Usuário não encontrado. Insira outro nome"); // Exibindo mensagem de erro
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                showErrorDialog("Ocorreu um erro de rede. Verifique sua conexão."); // Exibindo mensagem de erro
            }
        });
    }
}
