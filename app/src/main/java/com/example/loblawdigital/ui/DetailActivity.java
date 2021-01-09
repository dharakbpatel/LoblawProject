package com.example.loblawdigital.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.loblawdigital.R;
import com.example.loblawdigital.model.Entry;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Detail");
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(DashboardActivity.ENTRY_KEY)) {
            Entry entry = (Entry) intent.getSerializableExtra(DashboardActivity.ENTRY_KEY);
            TextView titleView = findViewById(R.id.detail_title);
            TextView priceView = findViewById(R.id.detail_price);
            TextView typeView = findViewById(R.id.detail_type);
            TextView codeView = findViewById(R.id.detail_code);
            ImageView imageView = findViewById(R.id.detail_image);
            Glide.with(this)
                    .load(entry.getImage())
                    .into(imageView);
            titleView.setText(entry.getName());
            priceView.setText(entry.getPrice());
            codeView.setText(entry.getCode());
            typeView.setText(entry.getType());
        } else {
            TextView errorView = findViewById(R.id.error_message);
            errorView.setVisibility(View.GONE);
        }
    }
}