package com.yusuf.kotaksaran;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yusuf.kotaksaran.Auth.AuthManager;
import com.yusuf.kotaksaran.Model.LaporanRequest;
import com.yusuf.kotaksaran.Model.ServerResponse;
import com.yusuf.kotaksaran.Model.Subjek;
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

public class MainActivity extends AppCompatActivity {
    LinearLayout navHistory, navProfile;
    EditText isiLaporan;
    TextView tvDokumen, laporanCount, tvPickDokumen;
    String mediaPath;
    Button btSend;
    Spinner subjekSpinner;
    AuthManager authManager;
    ApiInterface mApiInterface;
    Subjek bagian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        authManager = new AuthManager(MainActivity.this);
        String accessToken = authManager.getAccessToken();

        navHistory = findViewById(R.id.nav_history);
        navProfile = findViewById(R.id.nav_profile);
        btSend = findViewById(R.id.bt_send);
        subjekSpinner = findViewById(R.id.spinner_subjek);
        isiLaporan = findViewById(R.id.et_isi);
        tvDokumen = findViewById(R.id.dokumen_name);
        laporanCount = findViewById(R.id.laporan_count);
        tvPickDokumen =  findViewById(R.id.pick_dokumen);

        int maxLength = 500;
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(maxLength);
        isiLaporan.setFilters(filters);

        isiLaporan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                int charCount = maxLength - charSequence.length();
                laporanCount.setText(String.valueOf(charCount));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        Call<ServerResponse> subjekCall = mApiInterface.getSubjek("Bearer " + accessToken);
        subjekCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()) {
                    ServerResponse serverResponse = response.body();
                    if (serverResponse != null) {
                        List<Subjek> subjekList = serverResponse.getBagian();

                        List<String> subjekName = new ArrayList<>();
                        subjekName.add("Pilih Subjek");
                        for (Subjek subjek : subjekList) {
                            subjekName.add(subjek.getBagian());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                MainActivity.this,
                                android.R.layout.simple_spinner_item,
                                subjekName
                        );
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        subjekSpinner.setAdapter(adapter);

                        subjekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                if (position > 0) {
                                    bagian = subjekList.get(position - 1);
                                } else {
                                    bagian = null ;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                                bagian = null ;
                            }
                        });
                    } else {
                        Toast.makeText(MainActivity.this, "Response body is null", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Gagal Memuat Subjek", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Periksa Koneksi Anda" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        tvPickDokumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bagian == null || bagian.equals("Pilih Subjek")) {
                    Toast.makeText(MainActivity.this, "Pilih Subjek", Toast.LENGTH_SHORT).show();
                    return;
                }

                String subjek_laporan = bagian.getId_bagian();
                String isi_laporan = isiLaporan.getText().toString();
                MultipartBody.Part dokumenPart = null;

                if(!TextUtils.isEmpty(mediaPath)){
                    File file = new File(mediaPath);
                    RequestBody fileRequestBody = RequestBody.create(MediaType.parse("*/*"), file);
                    dokumenPart = MultipartBody.Part.createFormData("dokumen", file.getName(), fileRequestBody);
                }

                if (TextUtils.isEmpty(isi_laporan)) {
                    Toast.makeText(MainActivity.this, "Isi Laporan", Toast.LENGTH_SHORT).show();
                    return;
                }

                LaporanRequest laporanRequest = new LaporanRequest(null, subjek_laporan, isi_laporan, null, null, null, null);
                RequestBody subjekLaporanBody = RequestBody.create(MediaType.parse("text/plain"), subjek_laporan);
                RequestBody isiLaporanBody = RequestBody.create(MediaType.parse("text/plain"), isi_laporan);
                Call<ServerResponse> laporanCall;
                if (dokumenPart != null) {
                    laporanCall = mApiInterface.storeWithDocument("Bearer " + accessToken, dokumenPart, subjekLaporanBody, isiLaporanBody);
                } else {
                    laporanCall = mApiInterface.storeWithoutDocument("Bearer " + accessToken, laporanRequest);
                }

                laporanCall.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        if (response != null && response.isSuccessful()) {
                            if (response.body() != null) {
                                Toast.makeText(MainActivity.this, "Laporan berhasil Dikirim", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MainActivity.this, "Periksa Koneksi Anda" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        navHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(a);
            }
        });

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(b);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            mediaPath = getPath(selectedImageUri);
            String displayText = mediaPath.length() > 30 ? mediaPath.substring(0, 30) + "..." : mediaPath;
            tvDokumen.setText(displayText);
        }
    }

    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String filePath = cursor.getString(column_index);
            cursor.close();
            return filePath;
        } else {
            return uri.getPath();
        }
    }
}