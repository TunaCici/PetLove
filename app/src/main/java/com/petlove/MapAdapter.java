package com.petlove;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.MapViewHolder> {

    Context context;

    ArrayList<Integer> ids = new ArrayList<Integer>();
    ArrayList<String> locations = new ArrayList<String>();
    ArrayList<String> names = new ArrayList<String>();

    public MapAdapter(Context ct) {
        this.context = ct;

        // get the maps from the database
        DatabaseManager databaseManager = new DatabaseManager(context);

        Cursor cursor = databaseManager.getParks();

        // store the names
        while (cursor.moveToNext()) {
            locations.add(cursor.getString(1));
            names.add(cursor.getString(2));

        }

        // generate
        for (int i = 0; i < names.size(); i++) {
            ids.add(context.getResources().getIdentifier("park" + i, "drawable", context.getPackageName()));
        }
    }

    @NonNull
    @Override
    public MapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.item_map, parent, false);
        return new MapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MapViewHolder holder, int position) {
        holder.map_card_pic.setImageResource(ids.get(position));
        holder.map_card_text.setText(names.get(position));
        holder.map_card_text2.setText(locations.get(position));
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    public class MapViewHolder extends RecyclerView.ViewHolder {

        ImageView map_card_pic;
        TextView map_card_text;
        TextView map_card_text2;

        public MapViewHolder(@NonNull View itemView) {
            super(itemView);

            this.map_card_pic = itemView.findViewById(R.id.map_card_pic);
            this.map_card_text = itemView.findViewById(R.id.map_card_text);
            this.map_card_text2 = itemView.findViewById(R.id.map_card_text2);
        }
    }
}
