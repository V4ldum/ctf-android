package com.example.insecurelogging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText password = findViewById((R.id.password));
        EditText passwordConfirm = findViewById((R.id.passwordConfirm));
        String logString = getText((R.string.log)).toString();

        Button button = (Button) findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(passwordConfirm.getText().toString()))
                {
                    Log.d("LOG", logString + " " + password.getText().toString());
                    Toast.makeText(getApplicationContext(), R.string.success_main, Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), R.string.error_main, Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}