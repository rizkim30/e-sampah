package com.example.pkm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Tabungan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabungan);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}