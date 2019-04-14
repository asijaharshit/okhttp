package com.example.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.result);
        OkHttpClient client=new OkHttpClient();
        String url="https://api.github.com/search/users?q=user";
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Errorrrrr", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()){
                final String json_data=response.body().string();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("TAG", json_data);
                    }
                });
            }
            }
        });
    }
}
