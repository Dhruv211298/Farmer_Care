package com.shahdhruv.farmercare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class Register extends AppCompatActivity {
    EditText name,mobile_no,email,password,conf_password,location;
    AsyncHttpClient client;
    RequestParams rp;
    Button reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //getSupportActionBar().setTitle("Registration Form");
        name=findViewById(R.id.Name);
        mobile_no=findViewById(R.id.Mob_no);
        email=findViewById(R.id.Email);
        password=findViewById(R.id.Password);
        conf_password=findViewById(R.id.Con_Password);
        location=findViewById(R.id.Location);
        reg=findViewById(R.id.Register);
        client=new AsyncHttpClient();
        rp=new RequestParams();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty() || mobile_no.getText().toString().isEmpty() || mobile_no.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || conf_password.getText().toString().isEmpty() || location.getText().toString().isEmpty())
                {
                    Toast.makeText(Register.this, "Please Fill The Data...!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (password.getText().toString().equals(conf_password.getText().toString())) {
                        User user = new User();
                        rp.put("name", name.getText().toString());
                        rp.put("mobile_no", mobile_no.getText().toString());
                        rp.put("email", email.getText().toString());
                        rp.put("password", password.getText().toString());
                        rp.put("location", location.getText().toString());

                        client.post("https://farmercareadit53.000webhostapp.com/singup.php?", rp, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                Toast.makeText(Register.this, "Data Has Been Successfully Enter...!", Toast.LENGTH_SHORT).show();
                                name.setText("");
                                mobile_no.setText("");
                                email.setText("");
                                password.setText("");
                                conf_password.setText("");
                                location.setText("");
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                            }
                        });
                    } else {
                        Toast.makeText(Register.this, "Please Check The Password...!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
