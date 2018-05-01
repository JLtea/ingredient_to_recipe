package com.example.taele.finalproject;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private List<Item> listItems;
    private Context context;
    private Activity activity;

    public ListAdapter(List<Item> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customitem, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final Item item = listItems.get(position);
        holder.dishName.setText(item.getName());
        holder.publisher.setText(item.getPublisher());
        Picasso.with(context)
                .load(item.getPhoto())
                .into(holder.dishImage);

        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DisplayRecipe.class);
                intent.putExtra("rId", item.getrId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
    public static class ListViewHolder extends RecyclerView.ViewHolder {
        public TextView dishName;
        public ImageView dishImage;
        public TextView publisher;
        public RelativeLayout relative;
        public ListViewHolder(View itemView) {
            super(itemView);
            dishName = (TextView) itemView.findViewById(R.id.dishName);
            dishImage = (ImageView) itemView.findViewById(R.id.dishImage);
            publisher = (TextView) itemView.findViewById(R.id.publisher);
            relative = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }

    }
}
