package com.example.loblawdigital.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loblawdigital.R;
import com.example.loblawdigital.model.ApiResponse;
import com.example.loblawdigital.model.Entry;
import com.example.loblawdigital.remote.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    private List<Entry> entryList;
    private ProductAdapter productAdapter;
    private ProgressBar progressBar;
    private TextView errorTextView;
    public static final String ENTRY_KEY = "entry";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setTitle("Dashboard");
        progressBar = findViewById(R.id.progress);
        errorTextView = findViewById(R.id.error_message);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        entryList = new ArrayList<>();
        productAdapter = new ProductAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);

        productAdapter.setOnClickListener(entry -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(ENTRY_KEY, entry);
            startActivity(intent);
        });

        Call<ApiResponse> call = RetrofitClientInstance.get().getClient().getData();
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.code() == 200) {
                    ApiResponse entry = response.body();
                    if (entry != null) {
                        errorTextView.setVisibility(View.GONE);
                        entryList.addAll(entry.getEntries());
                        productAdapter.updateDataSet(entryList);
                    }
                } else {
                    errorTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.GONE);
                errorTextView.setVisibility(View.VISIBLE);
            }
        });
    }
}
