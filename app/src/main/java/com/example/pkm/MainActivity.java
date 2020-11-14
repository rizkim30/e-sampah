package com.example.pkm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void masuk1(View view)
    {
        Intent i=new Intent(MainActivity.this,Masuk.class);
        startActivity(i);
    }
    public void daftar1(View view)
    {
        Intent i=new Intent(MainActivity.this,Daftar.class);
        startActivity(i);
    }
}