package com.yusuf.kotaksaran;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    LinearLayout navHistory, navReport;
    TextView tvEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        navHistory = findViewById(R.id.nav_history);
        navReport = findViewById(R.id.nav_report);
        tvEdit = findViewById(R.id.tv_editProfile);

        navHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(ProfileActivity.this, HistoryActivity.class);
                startActivity(a);
                finish();
            }
        });

        navReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(b);
                finish();
            }
        });

        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(ProfileActivity.this, EditActivity.class);
                startActivity(c);
                finish();
            }
        });
    }
}