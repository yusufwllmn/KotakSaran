package com.yusuf.kotaksaran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    ImageView ivBack;
    Button btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ivBack = findViewById(R.id.iv_back);
        btnChange = findViewById(R.id.bt_changeprofile);

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
                Intent b = new Intent(EditActivity.this, ProfileActivity.class);
                startActivity(b);
                finish();
            }
        });
    }
}