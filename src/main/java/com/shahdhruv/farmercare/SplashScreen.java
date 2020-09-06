package com.shahdhruv.farmercare;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        image = findViewById(R.id.FinalLogo);
        User user = new User(SplashScreen.this);
        Thread temp = new Thread(){

            public void run(){
                try {
                    image.animate().rotation(360).setStartDelay(1);
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    if(user.getEmail()!="")
                    {
                        Intent i = new Intent(SplashScreen.this,activity_facilities.class);
                        i.putExtra("name",user.getName());
                        i.putExtra("email",user.getEmail());
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Intent i = new Intent(SplashScreen.this,LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }

        };
        temp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
