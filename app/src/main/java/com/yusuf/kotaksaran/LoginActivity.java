package com.yusuf.kotaksaran;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView tvRegister;

    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        etEmail = findViewById(R.id.et_loginEmail);
        etPassword = findViewById(R.id.et_loginPassword);
        btnLogin = findViewById(R.id.bt_login);
        tvRegister = findViewById(R.id.tv_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                login(email, password);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(b);
                finish();
            }
        });
    }

    public void login(String email, String password) {
        Call<ServerResponse> loginCall = mApiInterface.setUser(email, password);
        loginCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response != null && response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}