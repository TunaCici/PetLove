package com.petlove;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.database.CursorWindowCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PetHomeAdapter extends RecyclerView.Adapter<PetHomeAdapter.PetViewHolder> {

    Context context;

    ArrayList<Integer> ids = new ArrayList<Integer>();
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> ages = new ArrayList<String>();

    public PetHomeAdapter(Context ct, String path, String name, String age) {
        this.context = ct;

        // get the pets from the database
        DatabaseManager databaseManager = new DatabaseManager(context);

        Cursor cursor = databaseManager.getPets();

        // store the names
        while (cursor.moveToNext()) {
            names.add(cursor.getString(2));
            ages.add(cursor.getString(3));
        }

        // generate
        for (int i = 0; i < 4; i++) {
            if (i <= 1) {
                ids.add(context.getResources().getIdentifier("dog" + i, "drawable", context.getPackageName()));
            } else {
                ids.add(context.getResources().getIdentifier("cat" + (i-2), "drawable", context.getPackageName()));
            }
        }

    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.item_pet, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        holder.pet_card_pic.setImageResource(ids.get(position));
        holder.pet_card_text.setText(names.get(position) + " - " + ages.get(position) + " years old");
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {

        ImageView pet_card_pic;
        TextView pet_card_text;

        public PetViewHolder(@NonNull View itemView) {
            super(itemView);

            this.pet_card_pic = itemView.findViewById(R.id.pet_card_home_pic);
            this.pet_card_text = itemView.findViewById(R.id.pet_card_home_text);
        }
    }
}
