package com.shahdhruv.farmercare;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class result extends AppCompatActivity {
    TextView humidity, temperature, disease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        humidity = findViewById(R.id.table_humidity);
        temperature = findViewById(R.id.table_temperature);
        disease=findViewById(R.id.table_result);
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
                        disease.setText("Leaf Blight");
                    }
                    else if ((Temperature > 24.5 && Temperature < 32) && (Humidity >= 80)) {
                        disease.setText("Hispa");
                    }
                    else if ((Temperature > 25 && Temperature < 35) && (Humidity >= 90)) {
                        disease.setText("Leaf Smut");
                    }
                    else if ((Temperature > 16 && Temperature < 36) && (Humidity >= 86 && Humidity <= 100)) {
                        disease.setText("Brown Spot");
                    }
                    else {
                        disease.setText("No Disease");
                    }
                }
                else
                {
                    Toast.makeText(result.this, "There is a problem in data fetching from sensor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
