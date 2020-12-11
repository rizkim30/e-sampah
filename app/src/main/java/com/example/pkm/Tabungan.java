package com.example.pkm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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
    public String url1 = "http://156.67.221.101:1000/transaksi/";
    public Boolean cek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabungan);
        db = new DatabaseHelper(this);
        price = findViewById(R.id.idtab);
        tarik = findViewById(R.id.btntariktabungan);
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(url1).addConverterFactory(GsonConverterFactory.create()).build();
        final getCekTransaksi getCekTransaksi = new getCekTransaksi(db.getIdUser());
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<getCekTransaksi> call = jsonPlaceHolder.getCektransaksi(getCekTransaksi);
        cek = db.cek_id(db.getIdUser());
        if(cek==true){
            id = db.getIdOld(db.getIdUser());
            berat1 = Integer.parseInt(db.getBeratOld(db.getIdUser()));
            int hasil = 0;
            downloadSampah(id,berat1,hasil);
        }
        else{
            call.enqueue(new Callback<com.example.pkm.getCekTransaksi>() {
                @Override
                public void onResponse(Call<com.example.pkm.getCekTransaksi> call, Response<com.example.pkm.getCekTransaksi> response) {
                    getCekTransaksi getCekTransaksi1 = response.body();
                    String pesan = getCekTransaksi1.getPesan();
                    if(pesan.equals("0")){
                        if(cek==true){
                            Toast.makeText(getApplicationContext(),"User can transaksi",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            id = db.getIdOld(db.getIdUser());
                            berat1 = Integer.parseInt(db.getBeratOld(db.getIdUser()));
                            int hasil = Integer.parseInt(pesan);
                            downloadSampah(id,berat1,hasil);
                        }

                    }
                    else{
                        id = db.getIdOld(db.getIdUser());
                        berat1 = Integer.parseInt(db.getBeratOld(db.getIdUser()));
                        int hasil = Integer.parseInt(pesan);
                        downloadSampah(id,berat1,hasil);

                    }
                }

                @Override
                public void onFailure(Call<com.example.pkm.getCekTransaksi> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"lost connection",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void uploadTabungan(String a,String b,String c){
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(url1).addConverterFactory(GsonConverterFactory.create()).build();
        final postTransaksi postTransaksi = new postTransaksi(a,b,c);
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<postTransaksi>call = jsonPlaceHolder.getTransaksiPost(postTransaksi);
        call.enqueue(new Callback<com.example.pkm.postTransaksi>() {
            @Override
            public void onResponse(Call<com.example.pkm.postTransaksi> call, Response<com.example.pkm.postTransaksi> response) {
                postTransaksi postTransaksi1 = response.body();
                String pesan = postTransaksi1.getPesan();
                Toast.makeText(getApplicationContext(),pesan,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<com.example.pkm.postTransaksi> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Lost connection",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void downloadSampah(String a,final int b,final int c){
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        final getSampah getSampah = new getSampah(a);
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<getSampah> call = jsonPlaceHolder.getSampahPost(getSampah);
        call.enqueue(new Callback<com.example.pkm.getSampah>() {
            @Override
            public void onResponse(Call<com.example.pkm.getSampah> call, Response<com.example.pkm.getSampah> response) {
                getSampah getSampah1 = response.body();
                berat2 = Integer.parseInt(getSampah1.getBerat());
                nilai1 = berat2-b;
                rupiah = (nilai1*5)+c;
                uploadTabungan(db.getIdUser(),String.valueOf(nilai1),String.valueOf(rupiah));
                price.setText(String.valueOf(rupiah));
            }

            @Override
            public void onFailure(Call<com.example.pkm.getSampah> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"lost connection",Toast.LENGTH_SHORT).show();
            }
        });
    }
}