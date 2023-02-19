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

public class PetProfileAdapter extends RecyclerView.Adapter<PetProfileAdapter.PetViewHolder> {

    Context context;

    ArrayList<Integer> ids = new ArrayList<Integer>();
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> ages = new ArrayList<String>();
    ArrayList<String> types = new ArrayList<String>();

    public PetProfileAdapter(Context ct) {
        this.context = ct;

        // get the pets from the database
        DatabaseManager databaseManager = new DatabaseManager(context);

        Cursor cursor = databaseManager.getPets();

        // store the names
        while (cursor.moveToNext()) {
            names.add(cursor.getString(2));
            ages.add(cursor.getString(3));
            types.add(cursor.getString(4));

        }

        // generate
        for (int i = 0; i < 6; i++) {
            if (i <= 2) {
                ids.add(context.getResources().getIdentifier("dog" + i, "drawable", context.getPackageName()));
            } else {
                ids.add(context.getResources().getIdentifier("cat" + (i-3), "drawable", context.getPackageName()));
            }
        }
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.item_profile, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        holder.pet_card_profile_pic.setImageResource(ids.get(position));
        holder.pet_card_profile_text.setText(names.get(position));
        holder.pet_card_profile_text2.setText(types.get(position));
        holder.pet_card_profile_text3.setText(ages.get(position) + " years old");
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {

        ImageView pet_card_profile_pic;
        TextView pet_card_profile_text;
        TextView pet_card_profile_text2;
        TextView pet_card_profile_text3;

        public PetViewHolder(@NonNull View itemView) {
            super(itemView);

            this.pet_card_profile_pic = itemView.findViewById(R.id.pet_card_profile_pic);
            this.pet_card_profile_text = itemView.findViewById(R.id.pet_card_profile_text);
            this.pet_card_profile_text2 = itemView.findViewById(R.id.pet_card_profile_text2);
            this.pet_card_profile_text3 = itemView.findViewById(R.id.pet_card_profile_text3);
        }
    }
}
