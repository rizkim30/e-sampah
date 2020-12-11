package com.example.pkm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pilihan extends AppCompatActivity {
    ImageButton btntabung;
    ImageButton btnscan;
    DatabaseHelper db;
    public String url = "http://156.67.221.101:1000/user/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan);
        db = new DatabaseHelper(this);
        btntabung = findViewById(R.id.btntabung);
        btnscan = findViewById(R.id.btnscan);
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        btntabung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Pilihan.this,Tabungan.class);
                startActivity(i);
            }

        });
        btnscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Pilihan.this,QRCode.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        db.delete_old();
        finish();
    }
}