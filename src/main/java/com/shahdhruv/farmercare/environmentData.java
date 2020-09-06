package com.shahdhruv.farmercare;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class environmentData extends AppCompatActivity {

    TextView humidity, temperature;
    String disease;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment_data);
        humidity = findViewById(R.id.data_humidity);
        temperature = findViewById(R.id.data_temperature);

        FirebaseDatabase.getInstance().getReference().child("sensor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.child("temperature").getValue(String.class);
                String humid = dataSnapshot.child("humidity").getValue(String.class);

                humidity.setText(humid + " %");
                temperature.setText(temp + " \u2103");

                float Humidity=Float.parseFloat(humid);
                float Temperature=Float.parseFloat(temp);

                if(temp!=null && humid!=null)
                {
                    if ((Temperature > 25 && Temperature < 34) && (Humidity >= 70)) {
                        disease="Leaf Blight";
                    }
                    else if ((Temperature > 24.5 && Temperature < 32) && (Humidity >= 80)) {
                        disease="Hispa";
                    }
                    else if ((Temperature > 25 && Temperature < 35) && (Humidity >= 90)) {
                        disease="Leaf Smut";
                    }
                    else if ((Temperature > 16 && Temperature < 36) && (Humidity >= 86 && Humidity <= 100)) {
                        disease="Brown Spot";
                    }
                    else {
                        disease="No Disease (It's means healthy rice crop)";
                    }

                }
                else
                {
                    Toast.makeText(environmentData.this, "There is a problem in data fetching from sensor", Toast.LENGTH_SHORT).show();
                }
                Intent MainIntent = new Intent(environmentData.this,environmentData.class);
                MainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent MainPIntent = PendingIntent.getActivity(environmentData.this,0,MainIntent,PendingIntent.FLAG_ONE_SHOT);

                Intent YesIntent = new Intent(environmentData.this,Solution.class);
                YesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent YesPIntent = PendingIntent.getActivity(environmentData.this,0,YesIntent,PendingIntent.FLAG_ONE_SHOT);

                Intent NoIntent = new Intent(environmentData.this,result.class);
                NoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent NoPIntent = PendingIntent.getActivity(environmentData.this,0,NoIntent,PendingIntent.FLAG_ONE_SHOT);

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
                {
                    NotificationChannel channel=new NotificationChannel("FarmerCare_Notification","FarmerCare_Notification", NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager manager=getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(channel);
                }
                NotificationCompat.Builder builder=new NotificationCompat.Builder(environmentData.this,"FarmerCare_Notification")
                        .setContentTitle("Probable Disease: ")
                        .setSmallIcon(R.drawable.notification_logo)
                        .setAutoCancel(true)
                        .setContentText(disease)
                        .addAction(R.drawable.yes,"Yes",YesPIntent)
                        .addAction(R.drawable.no,"No",NoPIntent);
                NotificationManagerCompat manager=NotificationManagerCompat.from(environmentData.this);
                manager.notify(001,builder.build());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}