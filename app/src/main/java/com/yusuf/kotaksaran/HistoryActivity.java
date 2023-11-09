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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

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

        data();
    }

//    public void data() {
//        Call<ServerResponse> LaporanCall = mApiInterface.getLaporan();
//        LaporanCall.enqueue(new Callback<ServerResponse>() {
//            @Override
//            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {
//                List<Laporan> laporanList = response.body().getLaporan();
//                Log.d("Retrofit Get", "Jumlah data Petugas: " + String.valueOf(laporanList.size()));
//                LaporanAdapter laporanAdapter = new LaporanAdapter(laporanList);
//                mRecyclerView.setAdapter(laporanAdapter);
////                Onclick
////                listAccountPetugasAdapter.setOnItemClickCallback(data -> showSelectedCoffeeDrink(data));
//            }
//
//            @Override
//            public void onFailure(Call<ServerResponse> call, Throwable t) {
//                Log.e("Retrofit Get", t.toString());
//                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
//                builder.setMessage(t.getMessage());
//                builder.show();
//            }
//        });
//    }

    public void data() {
        Call<ServerResponse> LaporanCall = mApiInterface.getLaporan();
        LaporanCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                List<Laporan> laporanList = response.body().getLaporan();
                Log.d("Retrofit Get", "Jumlah data laporan: " + String.valueOf(laporanList.size()));

                for (Laporan laporan : laporanList) {

                    List<Subjek> subjekList = laporan.getSubjek_laporan();
                    for (Subjek subjek_laporan : subjekList) {
                        String subjek = subjek_laporan.getBagian();
                    }

                    String isi_laporan = laporan.getIsi_laporan();

                    String tanggal_lapor = laporan.getTanggal_lapor();

                    List<Status> statusList = laporan.getId_status();
                    for (Status statusInfo : statusList) {
                        String status = statusInfo.getStatus();
                    }

                    String dokumen = laporan.getDokumen();

                    List<User> userList = laporan.getId_pelapor();
                    for (User userDetail : userList) {
                        String id_user = userDetail.getId_user();
                    }
                }

                LaporanAdapter laporanAdapter = new LaporanAdapter(laporanList);
                mRecyclerView.setAdapter(laporanAdapter);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
                builder.setMessage(t.getMessage());
                builder.show();
            }
        });
    }
}