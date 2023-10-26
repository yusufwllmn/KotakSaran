package com.yusuf.kotaksaran.rest;

import com.yusuf.kotaksaran.lapor.GetNotes;
import com.yusuf.kotaksaran.lapor.PostPutDelNotes;
import com.yusuf.kotaksaran.lapor.ServerResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("rest_lapor.php")
    Call<GetNotes> getNotes(@Query("function") String function);

    @GET("rest_lapor.php")
    Call<GetNotes> getNotesById(@Query("function") String function,
                                @Query("subjek_laporan") String subjek_laporan);

    @Multipart
    @POST("rest_lapor.php")
    Call<ServerResponse> uploadFile(@Query("function") String function,
                                    @Part MultipartBody.Part file,
                                    @Part("file") RequestBody subjek_laporan);

    @Multipart
    @POST("rest_lapor.php")
    Call<PostPutDelNotes> postNotes(@Query("function") String function,
                                    @Part("subjek_laporan") RequestBody subjek_laporan,
                                    @Part("isi_laporan") RequestBody isi_laporan,
                                    @Part("tanggal_lapor") RequestBody tanggal_lapor,
                                    @Part("id_status") RequestBody id_status,
                                    @Part("dokumen") RequestBody dokumen,
                                    @Part("id_pelapor") RequestBody id_pelapor);
}
