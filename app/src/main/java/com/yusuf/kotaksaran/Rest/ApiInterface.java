package com.yusuf.kotaksaran.Rest;

import com.yusuf.kotaksaran.Model.Kategori;
import com.yusuf.kotaksaran.Model.Laporan;
import com.yusuf.kotaksaran.Model.LaporanRequest;
import com.yusuf.kotaksaran.Model.Pelapor;
import com.yusuf.kotaksaran.Model.PelaporRequest;
import com.yusuf.kotaksaran.Model.ServerResponse;
import com.yusuf.kotaksaran.Model.Subjek;
import com.yusuf.kotaksaran.Model.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    @GET("laporan")
    Call<ServerResponse> getSubjek(@Header("Authorization") String accessToken);

    @POST("laporan")
    Call<ServerResponse> store(@Header("Authorization") String accessToken,
                               @Body LaporanRequest laporanRequest);

    @GET("profile")
    Call<ServerResponse> getPelapor(@Header("Authorization") String accessToken);

    @GET("kategori")
    Call<ServerResponse> getKategori(@Header("Authorization") String accessToken);

    @PUT("profile/{id_pelapor}")
    Call<ServerResponse> update(@Path("id_pelapor") String id_pelapor,
                                @Header("Authorization") String accessToken,
                                @Body PelaporRequest pelaporRequest);
}
