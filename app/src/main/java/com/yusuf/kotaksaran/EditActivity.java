package com.yusuf.kotaksaran;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.yusuf.kotaksaran.Auth.AuthManager;
import com.yusuf.kotaksaran.Model.Kategori;
import com.yusuf.kotaksaran.Model.LaporanRequest;
import com.yusuf.kotaksaran.Model.Pelapor;
import com.yusuf.kotaksaran.Model.PelaporRequest;
import com.yusuf.kotaksaran.Model.ServerResponse;
import com.yusuf.kotaksaran.Model.Subjek;
import com.yusuf.kotaksaran.Model.User;
import com.yusuf.kotaksaran.Rest.ApiClient;
import com.yusuf.kotaksaran.Rest.ApiInterface;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {
    ImageView ivBack;
    Button btnChange;
    EditText etId, etNama, etAlamat, etTelephone;
    TextView testKategori;
    Spinner kategoriSpinner;
    Kategori kategori;
    AuthManager authManager;
    ApiInterface mApiInterface;
    List<Kategori> kategoriList;
    Pelapor pelaporCurrent;
    private String selectedKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        authManager = new AuthManager(EditActivity.this);
        String accessToken = authManager.getAccessToken();
        kategoriList = new ArrayList<>();

        ivBack = findViewById(R.id.iv_back);
        btnChange = findViewById(R.id.bt_changeprofile);
        kategoriSpinner = findViewById(R.id.spinner_kategori);
        etId = findViewById(R.id.et_edt_id);
        etNama = findViewById(R.id.et_edt_nama);
        etAlamat = findViewById(R.id.et_edt_alamat);
        etTelephone =  findViewById(R.id.et_edt_telephone);

        Call<ServerResponse> pelaporCall = mApiInterface.getPelapor("Bearer " + accessToken);
        pelaporCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response != null && response.isSuccessful()) {
                    ServerResponse serverResponse = response.body();
                    if (serverResponse != null) {
                        Pelapor pelapor = serverResponse.getPelapor();
                        pelaporCurrent = pelapor;
                        if (pelapor != null) {
                            if (pelapor.getId_identitas() != null) {
                                etId.setText(pelapor.getId_identitas());
                            } else {
                                etId.setText(null);
                            }
                            if (pelapor.getNama() != null) {
                                etNama.setText(pelapor.getNama());
                            } else {
                                etNama.setText(null);
                            }

                            if (pelapor.getAlamat() != null) {
                                etAlamat.setText(pelapor.getAlamat());
                            } else {
                                etAlamat.setText(null);
                            }

                            if (pelapor.getTelephone() != null) {
                                etTelephone.setText(pelapor.getTelephone());
                            } else {
                                etTelephone.setText(null);
                            }

                            if (pelapor.getId_kategori() != null) {
                                selectedKategori = pelapor.getId_kategori();
                            } else {
                                selectedKategori = null;
                            }

                            Call<ServerResponse> kategoriCall = mApiInterface.getKategori("Bearer " + accessToken);
                            kategoriCall.enqueue(new Callback<ServerResponse>() {
                                @Override
                                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                                    if (response.isSuccessful()) {
                                        ServerResponse serverResponse = response.body();
                                        if (serverResponse != null) {
                                            List<Kategori> kategoriList = serverResponse.getKategori();

                                            List<String> kategoriName = new ArrayList<>();
                                            kategoriName.add("Pilih Kategori");
                                            for (Kategori kategori : kategoriList) {
                                                kategoriName.add(kategori.getKategori());
                                            }

                                            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                                    EditActivity.this,
                                                    android.R.layout.simple_spinner_item,
                                                    kategoriName
                                            );
                                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            kategoriSpinner.setAdapter(adapter);

                                            if (selectedKategori != null) {
                                                for (int i = 0; i < kategoriList.size(); i++) {
                                                    if (kategoriList.get(i).getId_kategori().equals(selectedKategori)) {
                                                        kategoriSpinner.setSelection(i+1);
                                                        break;
                                                    }
                                                }
                                            }

                                            kategoriSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                                    if (position > 0) {
                                                        kategori = kategoriList.get(position - 1);
                                                    } else {
                                                        kategori = null ;
                                                    }
                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> parentView) {
                                                    kategori = null;
                                                }
                                            });
                                        } else {
                                            Toast.makeText(EditActivity.this, "Response body is null", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(EditActivity.this, "Gagal Memuat Subjek", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ServerResponse> call, Throwable t) {
                                    Toast.makeText(EditActivity.this, "Periksa Koneksi Anda" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(EditActivity.this, "Data Pelapor Tidak Tersedia", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(EditActivity.this, "Gagal Menyambungkan dengan Server", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditActivity.this, "Gagal Menyambungkan dengan Server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(EditActivity.this, "API call failed: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(EditActivity.this, ProfileActivity.class);
                startActivity(a);
                finish();
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_pelapor = pelaporCurrent.getId_pelapor();
                String id_identitas = etId.getText().toString();
                String nama = etNama.getText().toString();
                String kategoriSelected = (kategori != null) ? kategori.getId_kategori() : null;
                String alamat = etAlamat.getText().toString();
                String telephone = etTelephone.getText().toString();

                if (TextUtils.isEmpty(id_identitas)) {
                    Toast.makeText(EditActivity.this, "Isi ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(id_identitas)) {
                    Toast.makeText(EditActivity.this, "Isi Nama", Toast.LENGTH_SHORT).show();
                    return;
                }

                PelaporRequest pelaporRequest = new PelaporRequest(id_pelapor,id_identitas, nama, kategoriSelected,alamat , telephone);
                Call<ServerResponse> laporanCall = mApiInterface.update(id_pelapor, "Bearer " + accessToken, pelaporRequest);

                laporanCall.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        if (response != null && response.isSuccessful()) {
                            if (response.body() != null) {
                                Toast.makeText(EditActivity.this, "Edit Berhasil", Toast.LENGTH_SHORT).show();
                                Intent a = new Intent(EditActivity.this, ProfileActivity.class);
                                startActivity(a);
                                finish();
                            } else {
                                Toast.makeText(EditActivity.this, "Response body is null", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (response != null && response.errorBody() != null) {
                                try {
                                    String errorBody = response.errorBody().string();
                                    Log.e("LoginError", "Error response body: " + errorBody);
                                    Toast.makeText(EditActivity.this, errorBody, Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(EditActivity.this, "Update gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        Toast.makeText(EditActivity.this, "Periksa Koneksi Anda" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}