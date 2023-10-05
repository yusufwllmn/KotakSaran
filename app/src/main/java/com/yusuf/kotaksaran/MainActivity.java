package com.yusuf.kotaksaran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    LinearLayout navHistory, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navHistory = findViewById(R.id.nav_history);
        navProfile = findViewById(R.id.nav_profile);

        navHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a= new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(a);
                finish();
            }
        });

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b= new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(b);
                finish();
            }
        });
    }
}