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

public class DocAdapter extends RecyclerView.Adapter<DocAdapter.DocViewHolder> {

    Context context;

    ArrayList<Integer> ids = new ArrayList<Integer>();
    ArrayList<String> locations = new ArrayList<String>();
    ArrayList<String> names = new ArrayList<String>();

    public DocAdapter(Context ct) {
        this.context = ct;

        // get the docs from the database
        DatabaseManager databaseManager = new DatabaseManager(context);

        Cursor cursor = databaseManager.getDocs();

        // store the names
        while (cursor.moveToNext()) {
            locations.add(cursor.getString(1));
            names.add(cursor.getString(2));

        }

        // generate
        for (int i = 0; i < names.size(); i++) {
            ids.add(context.getResources().getIdentifier("doc" + i, "drawable", context.getPackageName()));
        }
    }

    @NonNull
    @Override
    public DocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.item_doc, parent, false);
        return new DocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocViewHolder holder, int position) {
        holder.doc_card_pic.setImageResource(ids.get(position));
        holder.doc_card_text.setText(names.get(position));
        holder.doc_card_text2.setText(locations.get(position));
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    public class DocViewHolder extends RecyclerView.ViewHolder {

        ImageView doc_card_pic;
        TextView doc_card_text;
        TextView doc_card_text2;

        public DocViewHolder(@NonNull View itemView) {
            super(itemView);

            this.doc_card_pic = itemView.findViewById(R.id.doc_card_pic);
            this.doc_card_text = itemView.findViewById(R.id.doc_card_text);
            this.doc_card_text2 = itemView.findViewById(R.id.doc_card_text2);
        }
    }
}
