package com.yusuf.kotaksaran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    LinearLayout navHistory, navReport;
    TextView tvEdit;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        navHistory = findViewById(R.id.nav_history);
        navReport = findViewById(R.id.nav_report);
        tvEdit = findViewById(R.id.tv_editProfile);
        btnLogout = findViewById(R.id.bt_logout);

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
                Intent d = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(d);
                finish();
            }
        });
    }
}