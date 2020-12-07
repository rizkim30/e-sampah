package com.example.pkm;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.zxing.Result;
import android.view.View;


public class QRCode extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    ImageButton button;
    public String url = "http://156.67.221.101:1000/data/";
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_code);
        button = findViewById(R.id.button);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }
//    private void generate(){
//        mScannerView= new ZXingScannerView(this);
//        setContentView(mScannerView);
//        mScannerView.setResultHandler(this);
//        mScannerView.startCamera();
//    }
    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        db = new DatabaseHelper(this);
        final String text;
        text = rawResult.getText().toString();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        getSampah getSampah = new getSampah(text);
        Call<getSampah>call = jsonPlaceHolder.getSampahPost(getSampah);
        Boolean check = db.checkOld(text);
        if(check==true){
            call.enqueue(new Callback<com.example.pkm.getSampah>() {
                @Override
                public void onResponse(Call<com.example.pkm.getSampah> call, Response<com.example.pkm.getSampah> response) {
                    getSampah getSampah1 = response.body();
                    String jenis = getSampah1.getJenis();
                    String berat = getSampah1.getBerat();
                    db.insertOld(text,berat,jenis);
                    Toast.makeText(getApplicationContext(),berat+"Berhasil"+jenis,Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
                @Override
                public void onFailure(Call<com.example.pkm.getSampah> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Lost connection",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            });
        }
        else {
            db.delete_old();
            call.enqueue(new Callback<com.example.pkm.getSampah>() {
                @Override
                public void onResponse(Call<com.example.pkm.getSampah> call, Response<com.example.pkm.getSampah> response) {
                    getSampah getSampah1 = response.body();
                    String jenis = getSampah1.getJenis();
                    String berat = getSampah1.getBerat();
                    db.insertOld(text,berat,jenis);
                    Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }

                @Override
                public void onFailure(Call<com.example.pkm.getSampah> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Lost Connection",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            });
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }
}

