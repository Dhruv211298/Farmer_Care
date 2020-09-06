package com.shahdhruv.farmercare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class forgot_password extends AppCompatActivity {
    AsyncHttpClient clint=new AsyncHttpClient();
    Button Submit;
    EditText email;
    RequestParams rp=new RequestParams();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        //getSupportActionBar().setTitle("Forgot Password");
        email=findViewById(R.id.user_email);
        final String Email;
        Email=email.getText().toString();

        Submit=findViewById(R.id.forgot_btn);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty())
                {
                    Toast.makeText(forgot_password.this, "Please Fill Email Address", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String url = "https://farmercareadit53.000webhostapp.com/forgot_password.php?";
                    rp.put("email",email.getText().toString());
                    clint.post(url,rp,new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            String value = String.valueOf(response);
                            //Toast.makeText(forgot_password.this, ""+value, Toast.LENGTH_SHORT).show();
                            try {
                                JSONObject obj=new JSONObject(value);
                                String obj_val=obj.getString("value");
                                if(obj_val.equals("null"))
                                {
                                    Toast.makeText(forgot_password.this, "Invalid Email...!", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(forgot_password.this, "Email Successfully Sent...!", Toast.LENGTH_SHORT).show();
                                    email.setText("");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }
}
