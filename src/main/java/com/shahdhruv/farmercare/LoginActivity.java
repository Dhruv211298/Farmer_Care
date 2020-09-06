package com.shahdhruv.farmercare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button login;
    AsyncHttpClient client=new AsyncHttpClient();
    RequestParams rp=new RequestParams();
    TextView tv_r,tv_f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getActionBar().setTitle("Login Page");
        email=findViewById(R.id.Email);
        password=findViewById(R.id.Password);
        tv_r=findViewById(R.id.textView_register);
        tv_f=findViewById(R.id.textView_forgotPassword);
        login=findViewById(R.id.Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty()||password.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Fill The Data!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    String url = "https://farmercareadit53.000webhostapp.com/login.php?";
                    rp.put("email", email.getText().toString());
                    rp.put("password", password.getText().toString());
                    client.post(url, rp, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            String value = String.valueOf(response);
                            try {
                                JSONObject obj = new JSONObject(value);
                                String obj_val=obj.getString("value");
                                if(obj_val.equals("null"))
                                {
                                    Toast.makeText(LoginActivity.this, "Invalid Email Or Password...!", Toast.LENGTH_SHORT).show();
                                }
                                    /*if (obj.getString("value").equals(null)) {
                                        Toast.makeText(LoginActivity.this, "Enter Valid Email Or Password", Toast.LENGTH_SHORT).show();
                                    }*/
                                //Toast.makeText(LoginActivity.this, ""+obj.getString("value"), Toast.LENGTH_SHORT).show();
                                else{
                                    Toast.makeText(LoginActivity.this, "Hello...!", Toast.LENGTH_SHORT).show();
                                    User user = new User(LoginActivity.this);
                                    user.setEmail(email.getText().toString());
                                    user.setEmail(obj.getString("email"));
                                    user.setName(obj.getString("name"));
                                    Intent i = new Intent(LoginActivity.this,activity_facilities.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    i.putExtra("email", user.getEmail());
                                    i.putExtra("name", user.getName());
                                    startActivity(i);
                                    finish();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        tv_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,Register.class);
                startActivity(i);
            }
        });
        tv_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,forgot_password.class);
                startActivity(i);
            }
        });

    }
}
