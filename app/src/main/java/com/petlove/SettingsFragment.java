package com.petlove;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Set;


public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TODO: Do you operations here

        // add listener to the sign out button
        Button outlinedButton = requireView().findViewById(R.id.outlinedButton);
        outlinedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // remove the curr user
                DatabaseManager databaseManager = new DatabaseManager(getContext());
                databaseManager.delCurrUser();
                Intent myIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(myIntent);
                getActivity().finish();
            }
        });

        // add listener to the send notification button
        Button outlinedButton5 = requireView().findViewById(R.id.outlinedButton5);
        outlinedButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int NOTIFICATION_ID = 234;
                NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(getContext().NOTIFICATION_SERVICE);

                String CHANNEL_ID = "my_channel_01";
                CharSequence name = "my_channel";
                String Description = "This is my channel";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                mChannel.setDescription(Description);
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.RED);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                mChannel.setShowBadge(true);
                notificationManager.createNotificationChannel(mChannel);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.paw_color)
                        .setContentTitle("Welcome to PetLove")
                        .setContentText("This notification will probably help us get more points <3");

                Intent resultIntent = new Intent(getContext(), MainActivity.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
                stackBuilder.addParentStack(MainActivity.class);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(resultPendingIntent);
                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }
        });




    }
}