package com.shahdhruv.farmercare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class feedback extends AppCompatActivity {
    Button feedback_btn;
    EditText feedback_resp;
    AsyncHttpClient client=new AsyncHttpClient();
    RequestParams rp=new RequestParams();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feedback_resp=findViewById(R.id.feedback_editText);
        feedback_btn=findViewById(R.id.feedback_btn);
        feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(feedback_resp.getText().toString().isEmpty())
                {
                    Toast.makeText(feedback.this, "Please Fill The Feedback...!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String url="https://farmercareadit53.000webhostapp.com/feedback.php?";
                    rp.put("email",getIntent().getStringExtra("email"));
                    rp.put("complain", feedback_resp.getText().toString());
                    client.post(url, rp, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            Toast.makeText(feedback.this, "Data Has been successfully enter", Toast.LENGTH_SHORT).show();
                            feedback_resp.setText("");
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    });
                }
            }
        });

    }
}
