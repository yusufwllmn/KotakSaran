package com.yusuf.kotaksaran.Rest;

import com.yusuf.kotaksaran.Model.Kategori;
import com.yusuf.kotaksaran.Model.Pelapor;
import com.yusuf.kotaksaran.Model.ServerResponse;
import com.yusuf.kotaksaran.Model.Subjek;
import com.yusuf.kotaksaran.Model.User;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("login")
    Call<ServerResponse> login(@Body User user);

    @POST("register")
    Call<ServerResponse> register(@Body User user);

    @POST("autologin")
    Call<ServerResponse> autoLogin(@Header("Authorization") String authorization);

    @POST("logout")
    Call<ServerResponse> logout(@Header("Authorization") String accessToken);

    @GET("riwayat")
    Call<ServerResponse> getLaporan(@Header("Authorization") String accessToken);

    @POST("laporan")
    Call<ServerResponse> store(@Body List<Subjek> subjek_laporan, String isi_laporan, String dokumen);

    @GET("profile")
    Call<ServerResponse> getPelapor(@Header("Authorization") String accessToken);

    @PUT("profile/{id_pelapor}")
    Call<ServerResponse> update(@Path("id_pelapor") int id_pelapor, @Body Pelapor pelapor);
}
