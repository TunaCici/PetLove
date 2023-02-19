package com.petlove;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TODO: Do you operations here
        // change the welcoming text
        TextView textViewWelcome = requireView().findViewById(R.id.textViewWelcome);

        // get current user name
        DatabaseManager databaseManager = new DatabaseManager(getContext());
        Cursor cursor = databaseManager.getCurrUser();

        String curr_id = "0";
        while (cursor.moveToNext()) {
            curr_id = cursor.getString(1);
        }

        cursor = databaseManager.getUsers();

        String name = "NO_NAME";
        while (cursor.moveToNext()) {
            if (cursor.getString(0).equals(curr_id)) {
                name = cursor.getString(1);
            }
        }

        // set the welcome text
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        textViewWelcome.setText("Welcome, " + name + "!");

        // set the pet cards
        PetHomeAdapter petHomeAdapter = new PetHomeAdapter(getContext(), "dog", "hello", "man");
        RecyclerView recyclerView = requireView().findViewById(R.id.recyclerHomeViewPets);
        recyclerView.setAdapter(petHomeAdapter);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }
}