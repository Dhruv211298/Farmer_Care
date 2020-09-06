package com.shahdhruv.farmercare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class profile_page extends AppCompatActivity {
    TextView name,mobile_no,email,location;
    AsyncHttpClient client=new AsyncHttpClient();
    RequestParams rp=new RequestParams();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        name=findViewById(R.id.textView_name);
        mobile_no=findViewById(R.id.textView_mobile);
        email=findViewById(R.id.textView_email);
        location=findViewById(R.id.textView_location);
        String url="https://farmercareadit53.000webhostapp.com/profile.php?";
        rp.put("email",getIntent().getStringExtra("email"));
        client.post(url,rp,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if(statusCode == 200)
                {
                    Toast.makeText(profile_page.this, "Success", Toast.LENGTH_SHORT).show();
                }
                else if(statusCode == 201)
                {
                    Toast.makeText(profile_page.this, "Created", Toast.LENGTH_SHORT).show();
                }
                else if(statusCode == 202)
                {
                    Toast.makeText(profile_page.this, "Accepted", Toast.LENGTH_SHORT).show();
                }
                else if(statusCode == 400)
                {
                    Toast.makeText(profile_page.this, "bad req.", Toast.LENGTH_SHORT).show();
                }
                String value=String.valueOf(response);
                //Toast.makeText(profile_page.this, ""+value, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject obj=new JSONObject(value);
                    User user=new User(profile_page.this);
                    user.setEmail(obj.getString("email"));
                    user.setName(obj.getString("name"));
                    user.setMobile_no(obj.getString("mobile_no"));
                    user.setLocation(obj.getString("location"));
                    name.setText(user.getName());
                    email.setText(user.getEmail());
                    mobile_no.setText(user.getMobile_no());
                    location.setText(user.getLocation());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(profile_page.this, "Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
