package com.faizah.sayon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by USER on 24/12/2018.
 */

public class Utama extends AppCompatActivity {

    Button btn_login, btn_regis;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_regis = (Button) findViewById(R.id.btn_regis);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent login = new Intent(Utama.this, Login.class);
                startActivity(login);
            }
        });

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regis = new Intent(Utama.this, Register.class);
                startActivity(regis);
            }
        });

    }
}

