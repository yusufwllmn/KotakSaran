package com.yusuf.kotaksaran;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yusuf.kotaksaran.Adapter.LaporanAdapter;
import com.yusuf.kotaksaran.Auth.AuthManager;
import com.yusuf.kotaksaran.Model.Laporan;
import com.yusuf.kotaksaran.Model.ServerResponse;
import com.yusuf.kotaksaran.Model.Status;
import com.yusuf.kotaksaran.Model.Subjek;
import com.yusuf.kotaksaran.Model.User;
import com.yusuf.kotaksaran.Rest.ApiClient;
import com.yusuf.kotaksaran.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    LinearLayout navReport, navProfile;

    ApiInterface mApiInterface;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    AuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        authManager = new AuthManager(HistoryActivity.this);
        String accessToken = authManager.getAccessToken();

        navReport = findViewById(R.id.nav_report);
        navProfile = findViewById(R.id.nav_profile);

        navReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(a);
            }
        });

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(HistoryActivity.this, ProfileActivity.class);
                startActivity(b);
            }
        });

        Call<ServerResponse> laporanCall = mApiInterface.getLaporan("Bearer " + accessToken);
        laporanCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response != null && response.isSuccessful()) {
                    ServerResponse serverResponse = response.body();
                    if (serverResponse != null) {
                        List<Laporan> laporanList = serverResponse.getLaporan();

                        if (laporanList != null && !laporanList.isEmpty()) {
                            Log.d("Retrofit Get", "Jumlah data laporan: " + laporanList.size());

                            LaporanAdapter laporanAdapter = new LaporanAdapter(laporanList);
                            mRecyclerView.setAdapter(laporanAdapter);

                        } else {
                            Log.d("Retrofit Get", "Tidak ada data laporan.");
                        }
                    } else {
                        Log.e("Retrofit Get", "Respons null");
                    }
                } else {
                    Log.e("Retrofit Get", "Respons tidak berhasil atau null");
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e("Retrofit Get", "Gagal melakukan panggilan API: " + t.getLocalizedMessage());

                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
                builder.setMessage("Gagal melakukan panggilan API: " + t.getLocalizedMessage());
                builder.show();
            }
        });
    }

}