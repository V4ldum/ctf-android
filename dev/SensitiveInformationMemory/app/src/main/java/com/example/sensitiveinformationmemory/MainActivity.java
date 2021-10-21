package com.example.sensitiveinformationmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String pass1;
    Random generator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generator = new Random();
        int leftLimit = 'a';
        int rightLimit = 'z';
        int stringLength = 10;

        // Generating Password
        StringBuilder buffer = new StringBuilder(stringLength);
        for(int i = 0; i < stringLength; i++) {
            int random = leftLimit + (int)(generator.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) random);
        }
        pass1 = buffer.toString();

        Button btn = findViewById(R.id.login);
        EditText txt = findViewById(R.id.password);
        btn.setOnClickListener(view -> {
            if(txt.getText().toString().equals(pass1)) {
                Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), R.string.error_main, Toast.LENGTH_LONG).show();
            }
        });

    }
}