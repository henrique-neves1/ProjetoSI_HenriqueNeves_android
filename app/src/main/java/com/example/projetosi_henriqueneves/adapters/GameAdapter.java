package com.example.projetosi_henriqueneves.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projetosi_henriqueneves.R;
import com.example.projetosi_henriqueneves.model.Game;

import java.util.List;

public class GameAdapter extends BaseAdapter {
    private List<Game> games;
    private Context context;
    private OnGameClickListener listener;

    public interface OnGameClickListener {
        void onGameClick(Game game);
    }

    public GameAdapter(Context context, List<Game> games, OnGameClickListener listener) {
        this.context = context;
        this.games = games;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return games.size();
    }

    @Override
    public Object getItem(int position) {
        return games.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            // Inflate the item layout
            convertView = LayoutInflater.from(context).inflate(R.layout.item_game, parent, false);

            // Initialize the ViewHolder
            holder = new ViewHolder();
            holder.cover = convertView.findViewById(R.id.cover);
            holder.name = convertView.findViewById(R.id.name);
            holder.price = convertView.findViewById(R.id.price);
            convertView.setTag(holder);
        } else {
            // Reuse the ViewHolder
            holder = (ViewHolder) convertView.getTag();
        }

        // Get the current game
        Game game = games.get(position);

        // Populate the item with game data
        Glide.with(context).load(game.getCoverUrl()).into(holder.cover);
        holder.name.setText(game.getName());
        holder.price.setText(String.format("$%.2f", game.getPrice()));

        // Set the click listener
        convertView.setOnClickListener(v -> listener.onGameClick(game));

        return convertView;
    }

    // ViewHolder class for caching view references
    private static class ViewHolder {
        ImageView cover;
        TextView name;
        TextView price;
    }
}
