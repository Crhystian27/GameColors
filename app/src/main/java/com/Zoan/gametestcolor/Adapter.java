package com.Zoan.gametestcolor;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Colors> colors;
    Context context;
    ImageView imageView;
    Adapter adapter;
    TextView number1, number2;
    Integer contador1, contador2;
    Integer val;

    public Adapter(List<Colors> colors, Context context, ImageView imageView, Integer contador1, Integer contador2, TextView number1, TextView number2, Integer val) {
        this.colors = colors;
        this.context = context;
        this.imageView = imageView;
        this.number1 = number1;
        this.number2 = number2;
        this.contador1 = contador1;
        this.contador2 = contador2;
        this.val = val;
        this.adapter = this;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_colors, parent, false);
        return new ViewHolder(view, colors, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.image.setBackgroundColor(Color.parseColor(colors.get(position).getColor()));
        imageView.setBackgroundColor(Color.parseColor(colors.get(val).getColor()));

        holder.image.setOnClickListener(view -> {

            if (colors.get(position).getId().equals(colors.get(val).getId())) {
                contador1++;
                number1.setText("" + contador1);
            } else {
                contador2++;
                number2.setText("" + contador2);
            }

            Collections.shuffle(colors);
            Random random = new Random();
            val = random.nextInt(colors.size());
            imageView.setBackgroundColor(Color.parseColor(colors.get(val).getColor()));

            adapter.notifyDataSetChanged();

        });

    }

    public void reset() {
        contador1 = 0;
        contador2 = 0;
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        List<Colors> colors;
        Context context;
        ImageView image;

        public ViewHolder(@NonNull View itemView, List<Colors> colors, Context context) {
            super(itemView);
            this.colors = colors;
            this.context = context;
            image = itemView.findViewById(R.id.row_image);
        }
    }
}
