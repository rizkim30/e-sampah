package com.example.pkm;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceHolder {
    @POST("register")
    Call<registerPost> getregisterPost(@Body registerPost registerPost);
    @POST("login")
    Call<loginPost>getloginPost(@Body loginPost loginPost);
    @POST("sampah")
    Call<getSampah>getSampahPost(@Body getSampah getSampah);

}
