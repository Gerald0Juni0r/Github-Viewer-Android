package com.example.github_viewer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github_viewer.model.Repository;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {
    private List<Repository> repositoryList;

    public RepositoryAdapter(List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        Repository repo = repositoryList.get(position);
        holder.txtRepoName.setText(repo.getName());
        holder.txtRepoLanguage.setText(repo.getLanguage() != null ? repo.getLanguage() : "Desconhecido");
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    public static class RepositoryViewHolder extends RecyclerView.ViewHolder {
        TextView txtRepoName, txtRepoLanguage;

        public RepositoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRepoName = itemView.findViewById(R.id.txtRepoName);
            txtRepoLanguage = itemView.findViewById(R.id.txtRepoLanguage);
        }
    }
}
