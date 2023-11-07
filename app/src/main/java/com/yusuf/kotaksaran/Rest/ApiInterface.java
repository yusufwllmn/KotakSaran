package com.yusuf.kotaksaran.Rest;

import com.yusuf.kotaksaran.Model.ServerResponse;
import retrofit2.http.GET;
import retrofit2.Call;

public interface ApiInterface {

    @GET("laporan")
    Call<ServerResponse> getLaporan;

}
