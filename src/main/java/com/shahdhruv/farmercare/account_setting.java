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

public class account_setting extends AppCompatActivity {
    EditText name,mobile_no,password,location,conf_password;
    Button update_btn;
    AsyncHttpClient client=new AsyncHttpClient();
    RequestParams rp=new RequestParams();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        name=findViewById(R.id.Name);
        mobile_no=findViewById(R.id.Mobile_no);
        password=findViewById(R.id.Password);
        location=findViewById(R.id.Location);
        conf_password=findViewById(R.id.Con_Password);
        update_btn=findViewById(R.id.Update_btn);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty() && mobile_no.getText().toString().isEmpty() && password.getText().toString().isEmpty() && location.getText().toString().isEmpty())
                {
                    Toast.makeText(account_setting.this, "Please Fill The Data...!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.getText().toString().equals(conf_password.getText().toString())) {
                        String url = "https://farmercareadit53.000webhostapp.com/update_profiles.php?";
                        rp.put("email", getIntent().getStringExtra("email"));
                        rp.put("name", name.getText().toString());
                        rp.put("mobile_no", mobile_no.getText().toString());
                        rp.put("password", password.getText().toString());
                        rp.put("location", location.getText().toString());

                        client.post(url, rp, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                Toast.makeText(account_setting.this, "Data Successfully Updated...!", Toast.LENGTH_SHORT).show();
                                name.setText("");
                                mobile_no.setText("");
                                password.setText("");
                                conf_password.setText("");
                                location.setText("");
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(account_setting.this, "Please Check The Password...!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
