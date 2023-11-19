package com.yusuf.kotaksaran;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yusuf.kotaksaran.Auth.AuthManager;
import com.yusuf.kotaksaran.Model.Laporan;
import com.yusuf.kotaksaran.Model.ServerResponse;
import com.yusuf.kotaksaran.Model.Subjek;
import com.yusuf.kotaksaran.Model.User;
import com.yusuf.kotaksaran.Rest.ApiClient;
import com.yusuf.kotaksaran.Rest.ApiInterface;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    LinearLayout navHistory, navProfile;
    EditText isiLaporan, subjekLaporan;
    TextView dokumen;
    Button btSend;
    AuthManager authManager;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        authManager = new AuthManager(MainActivity.this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        navHistory = findViewById(R.id.nav_history);
        navProfile = findViewById(R.id.nav_profile);
        btSend = findViewById(R.id.bt_send);
        subjekLaporan = findViewById(R.id.et_subjek);
        isiLaporan = findViewById(R.id.et_isi);
        dokumen = findViewById(R.id.dokumen);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, authManager.getAccessToken(), Toast.LENGTH_SHORT).show();
                String isi = isiLaporan.getText().toString();
                String dkmn = dokumen.getText().toString();

                if (isiLaporan.getText().toString().isEmpty() || subjekLaporan.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Form harus di isi", Toast.LENGTH_SHORT).show();
                    return;
                }

                Laporan laporan = new Laporan(null, null, isi,null, null, dkmn, null);
                Call<ServerResponse> laporanCall = mApiInterface.store(null, isi, dkmn);

                laporanCall.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        if (response != null && response.isSuccessful()) {
                            if (response.body() != null) {
                                Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Response body is null", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (response != null && response.errorBody() != null) {
                                try {
                                    String errorBody = response.errorBody().string();
                                    Log.e("LoginError", "Error response body: " + errorBody);
                                    Toast.makeText(MainActivity.this, errorBody, Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        Log.e("LoginError", "Login failed", t);
                        Toast.makeText(MainActivity.this, "Login failed: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        navHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a= new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(a);
            }
        });

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b= new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(b);
            }
        });
    }
}