package com.yusuf.kotaksaran.Rest;

import com.yusuf.kotaksaran.Model.ServerResponse;
import com.yusuf.kotaksaran.Model.User;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {

    @POST("login")
    Call<ServerResponse> getUser();

    @POST("register")
    Call<ServerResponse> setUser(@Body String email, String password);

    @GET("riwayat")
    Call<ServerResponse> getLaporan();

    @POST("laporan")
    Call<ServerResponse> setLaporan();

    @GET("profile")
    Call<ServerResponse> getPelapor();

    @PUT("profile")
    Call<ServerResponse> setPelapor();
}
