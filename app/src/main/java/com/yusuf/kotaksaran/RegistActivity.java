package com.yusuf.kotaksaran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegistActivity extends AppCompatActivity {

    Button btnRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        btnRegist = findViewById(R.id.bt_regist);

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(RegistActivity.this, MainActivity.class);
                startActivity(a);
                finish();
            }
        });
    }
}