package com.example.pkm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Daftar extends AppCompatActivity {
    EditText nama,email,pass;
    ImageButton ins;
    public String url = "http://156.67.221.101:1000/user/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        nama = findViewById(R.id.innama);
        email = findViewById(R.id.inemail);
        pass = findViewById(R.id.inpass);
        ins = findViewById(R.id.btndaftar2);
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        final JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nama.getText().toString().equals("") && email.getText().toString().equals("") && pass.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Silahkan Lengkapi Data",Toast.LENGTH_LONG).show();
                }
                else{
                    String a = nama.getText().toString();
                    String b = email.getText().toString();
                    String c = pass.getText().toString();
                    registerPost registerPost = new registerPost(a,b,c);
                    Call<registerPost> call = jsonPlaceHolder.getregisterPost(registerPost);
                    call.enqueue(new Callback<com.example.pkm.registerPost>() {
                        @Override
                        public void onResponse(Call<com.example.pkm.registerPost> call, Response<com.example.pkm.registerPost> response) {
                            Toast.makeText(getApplicationContext(),"Anda berhasil Daftar",Toast.LENGTH_LONG).show();
                            nama.setText("");
                            email.setText("");
                            pass.setText("");
                        }

                        @Override
                        public void onFailure(Call<com.example.pkm.registerPost> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"Lost Connection",Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }
        });

    }

}