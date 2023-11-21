package com.yusuf.kotaksaran;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.yusuf.kotaksaran.Auth.AuthManager;
import com.yusuf.kotaksaran.Model.Kategori;
import com.yusuf.kotaksaran.Model.Laporan;
import com.yusuf.kotaksaran.Model.Pelapor;
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

public class ProfileActivity extends AppCompatActivity {

    LinearLayout navHistory, navReport;
    TextView tvEdit, tvEmail, tvId, tvPassword, tvNama, tvKategori, tvAlamat, tvTelephone;
    Button btnLogout;

    ApiInterface mApiInterface;
    AuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        authManager = new AuthManager(ProfileActivity.this);
        String accessToken = authManager.getAccessToken();

        navHistory = findViewById(R.id.nav_history);
        navReport = findViewById(R.id.nav_report);
        tvEdit = findViewById(R.id.tv_editProfile);
        btnLogout = findViewById(R.id.bt_logout);
        tvEmail = findViewById(R.id.tv_p_email);
        tvId = findViewById(R.id.tv_p_id);
        tvPassword = findViewById(R.id.tv_p_password);
        tvNama = findViewById(R.id.tv_p_nama);
        tvKategori = findViewById(R.id.tv_p_kategori);
        tvAlamat = findViewById(R.id.tv_p_alamat);
        tvTelephone = findViewById(R.id.tv_p_telephone);

        Call<ServerResponse> pelaporCall = mApiInterface.getPelapor("Bearer " + accessToken);
        pelaporCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response != null && response.isSuccessful()) {
                    ServerResponse serverResponse = response.body();
                    if (serverResponse != null) {
                        Pelapor pelapor = serverResponse.getPelapor();
                        if (pelapor != null) {
                            User user = pelapor.getUser();
                            if (user.getEmail() != null) {
                                tvEmail.setText(user.getEmail());
                            } else {
                                tvEmail.setText("Belum dilengkapi");
                                tvEmail.setTextColor(ContextCompat.getColor(ProfileActivity.this, R.color.orange));
                            }

                            if (pelapor.getId_identitas() != null) {
                                tvId.setText(pelapor.getId_identitas());
                            } else {
                                tvId.setText("Belum dilengkapi");
                                tvId.setTextColor(ContextCompat.getColor(ProfileActivity.this, R.color.orange));
                            }

                            if (pelapor.getNama() != null) {
                                tvNama.setText(pelapor.getNama());
                            } else {
                                tvNama.setText("Belum dilengkapi");
                                tvNama.setTextColor(ContextCompat.getColor(ProfileActivity.this, R.color.orange));
                            }

                            Kategori kategori = pelapor.getkategori();
                            if (pelapor.getkategori() != null) {
                                tvKategori.setText(kategori.getKategori());
                            } else {
                                tvKategori.setText("Belum dilengkapi");
                                tvKategori.setTextColor(ContextCompat.getColor(ProfileActivity.this, R.color.orange));
                            }

                            if (pelapor.getAlamat() != null) {
                                tvAlamat.setText(pelapor.getAlamat());
                            } else {
                                tvAlamat.setText("Belum dilengkapi");
                                tvAlamat.setTextColor(ContextCompat.getColor(ProfileActivity.this, R.color.orange));
                            }

                            if (pelapor.getTelephone() != null) {
                                tvTelephone.setText(pelapor.getTelephone());
                            } else {
                                tvTelephone.setText("Belum dilengkapi");
                                tvTelephone.setTextColor(ContextCompat.getColor(ProfileActivity.this, R.color.orange));
                            }
                        } else {
                            Toast.makeText(ProfileActivity.this, "Data Pelapor Tidak Tersedia", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ProfileActivity.this, "Gagal Menyambungkan dengan Server", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ProfileActivity.this, "Gagal Menyambungkan dengan Server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "API call failed: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        navHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(ProfileActivity.this, HistoryActivity.class);
                startActivity(a);
            }
        });

        navReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(b);
            }
        });

        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(ProfileActivity.this, EditActivity.class);
                startActivity(c);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ServerResponse> logoutCall = mApiInterface.logout("Bearer " + accessToken);
                logoutCall.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        if (response != null && response.isSuccessful()) {
                            authManager.clearAccessToken();
                            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                            Toast.makeText(ProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(ProfileActivity.this, "Logout failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        Toast.makeText(ProfileActivity.this, "Logout failed: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}