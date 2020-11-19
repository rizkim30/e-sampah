package com.example.pkm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Masuk extends AppCompatActivity {
    EditText email,pass;
    ImageButton login;
    public String url = "http://156.67.221.101:1000/user/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);
        email=findViewById(R.id.inpemail);
        pass=findViewById(R.id.passmasuk);
        login=findViewById(R.id.btnmasuk2);
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals("")&&pass.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"lengkapi data",Toast.LENGTH_SHORT).show();
                }
                else{
                    loginPost loginPost = new loginPost(email.getText().toString(),pass.getText().toString());
                    JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
                    Call<loginPost> call = jsonPlaceHolder.getloginPost(loginPost);
                    call.enqueue(new Callback<com.example.pkm.loginPost>() {
                        @Override
                        public void onResponse(Call<com.example.pkm.loginPost> call, Response<com.example.pkm.loginPost> response) {
                            loginPost loginPost1 = response.body();
                            String pesan = loginPost1.getId();
                            if(pesan.equals("Berhasil")){
                                Toast.makeText(getApplicationContext(),"Login "+pesan ,Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(Masuk.this,Pilihan.class);
                                startActivity(i);
                                onBackPressed();

                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Gagal login",Toast.LENGTH_SHORT).show();
                                email.setText("");
                                pass.setText("");
                            }

                        }

                        @Override
                        public void onFailure(Call<com.example.pkm.loginPost> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"lost connection",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
    });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}