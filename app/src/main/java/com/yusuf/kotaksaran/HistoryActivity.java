package com.yusuf.kotaksaran;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HistoryActivity extends AppCompatActivity {

    LinearLayout navReport, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        navReport = findViewById(R.id.nav_report);
        navProfile = findViewById(R.id.nav_profile);

        navReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(a);
                finish();
            }
        });

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(HistoryActivity.this, ProfileActivity.class);
                startActivity(b);
                finish();
            }
        });
    }
}