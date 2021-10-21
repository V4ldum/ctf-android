package com.example.insecuredatastorage3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static java.io.File.createTempFile;

public class MainActivity extends AppCompatActivity {
    File fichUsername;
    File fichPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            fichUsername = createTempFile("usr",".tmp");
            fichPassword = createTempFile("pwd",".tmp");
            fichUsername.setReadable(true);
            fichUsername.setWritable(true);
            fichPassword.setReadable(true);
            fichPassword.setWritable(true);

            FileWriter fwUser = new FileWriter(fichUsername);
            FileWriter fwPwd = new FileWriter(fichPassword);

            fwUser.write("sdd");
            fwPwd.write("derecursification");

            fwUser.close();
            fwPwd.close();

        } catch (IOException e) {
            Log.e("MainActivity", e.getMessage());
        }
        EditText username = findViewById((R.id.username));
        EditText password = findViewById((R.id.password));

        Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = null;
                String pwd = null;
                try {
                    FileReader frUser = new FileReader(fichUsername);
                    FileReader frPwd = new FileReader(fichPassword);
                    BufferedReader brUser = new BufferedReader(frUser);
                    BufferedReader brPwd = new BufferedReader(frPwd);
                    login = brUser.readLine();
                    pwd = brPwd.readLine();
                } catch (FileNotFoundException e) {
                    Log.e("MainActivity", e.getMessage());
                } catch (IOException e) {
                    Log.e("MainActivity", e.getMessage());
                }

                if(login != null && pwd != null)
                {
                    if(username.getText().toString().equals(login) && password.getText().toString().equals(pwd))
                    {
                        Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), R.string.error_main, Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
    }
}