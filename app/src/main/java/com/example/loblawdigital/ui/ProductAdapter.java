package com.example.loblawdigital.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loblawdigital.R;
import com.example.loblawdigital.model.Entry;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private final Context context;
    private final List<Entry> entryList;
    private OnClick callback;

    public ProductAdapter(@NonNull Context context) {
        this.context = context;
        this.entryList = new ArrayList<>();
    }

    public void setOnClickListener(@Nullable OnClick clickCallback) {
        this.callback = clickCallback;
    }

    public void updateDataSet(@NonNull List<Entry> entryList) {
        this.entryList.addAll(entryList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.product_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(entryList.get(position));
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    public interface OnClick {
        void onItemClick(Entry entry);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView nameView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.product_name);
            imageView = itemView.findViewById(R.id.product_image);
            itemView.setOnClickListener(this);
        }

        public void bindData(@NonNull Entry entry) {
            nameView.setText(entry.getName());
            Glide.with(context)
                    .load(entry.getImage())
                    .into(imageView);
        }

        @Override
        public void onClick(View view) {
            if (callback != null) {
                callback.onItemClick(entryList.get(getAdapterPosition()));
            }
        }
    }
}
