package com.example.pkm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tabungan extends AppCompatActivity {
    EditText price;
    ImageButton tarik;
    public String id;
    public int berat1,berat2,nilai1,rupiah;
    DatabaseHelper db;
    public String url = "http://156.67.221.101:1000/data/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabungan);
        db = new DatabaseHelper(this);
        price = findViewById(R.id.idtab);
        tarik = findViewById(R.id.btntariktabungan);
        id = db.getIdOld();
        berat1 = Integer.parseInt(db.getBeratOld(id));
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        final getSampah getSampah = new getSampah(id);
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<getSampah>call = jsonPlaceHolder.getSampahPost(getSampah);
        call.enqueue(new Callback<com.example.pkm.getSampah>() {
            @Override
            public void onResponse(Call<com.example.pkm.getSampah> call, Response<com.example.pkm.getSampah> response) {
                getSampah getSampah1 = response.body();
                berat2 = Integer.parseInt(getSampah1.berat);
                nilai1 = berat2-berat1;
                rupiah = nilai1*5;
                price.setText(rupiah+",00");
            }
            @Override
            public void onFailure(Call<com.example.pkm.getSampah> call, Throwable t) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}