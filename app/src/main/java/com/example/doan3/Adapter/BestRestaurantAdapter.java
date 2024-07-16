package com.example.doan3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.doan3.Model.Restaurant;
import com.example.doan3.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BestRestaurantAdapter extends RecyclerView.Adapter<BestRestaurantAdapter.viewholder> {
    ArrayList<Restaurant> items;
    Context context;

    public BestRestaurantAdapter(ArrayList<Restaurant> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BestRestaurantAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_best_restaurant, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BestRestaurantAdapter.viewholder holder, int position) {
        holder.txtName.setText(items.get(position).getName());
        holder.txtStar.setText(items.get(position).getStar());
        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.imgRestaurant);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView txtName, txtStar;
        ImageView imgRestaurant;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtNameRestaurant);
            txtStar = itemView.findViewById(R.id.txtStar);
            imgRestaurant = itemView.findViewById(R.id.imgBestRestaurant);


        }
    }
}
