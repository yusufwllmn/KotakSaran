package com.yusuf.kotaksaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.yusuf.kotaksaran.Auth.AuthManager;
import com.yusuf.kotaksaran.Model.ServerResponse;
import com.yusuf.kotaksaran.Rest.ApiClient;
import com.yusuf.kotaksaran.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    Handler h = new Handler();

    ApiInterface mApiInterface;
    AuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        authManager = new AuthManager(SplashActivity.this);

        if (!authManager.getAccessToken().isEmpty()) {
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String accessToken = authManager.getAccessToken();
                    attemptAutoLogin(accessToken);
                }
            }, 800);
        } else {
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent s = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(s);
                    finish();
                }
            }, 800);
        }
    }

    private void attemptAutoLogin(String accessToken) {
        Call<ServerResponse> autoLoginCall = mApiInterface.autoLogin("Bearer " + accessToken);

        autoLoginCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()) {
                    ServerResponse serverResponse = response.body();
                    if (serverResponse != null) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SplashActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SplashActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(SplashActivity.this, "Periksa Koneksi Anda" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}