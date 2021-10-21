package com.example.insecuredatastorage2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        try
        {
            db = openOrCreateDatabase("credentials.sqlite", MODE_PRIVATE, null);
            db.execSQL("Drop table if exists user;");
            db.execSQL("Create table if not exists user (username varchar2(200), password varchar2(200));");
            db.execSQL("Insert into user values ('cisco', 'cisco');");
            db.close();
        }
        catch (Exception e)
        {
            Log.e("MainActivity", "erreur création");
        }
        setContentView(R.layout.activity_main);

        EditText username = findViewById((R.id.username));
        EditText password = findViewById((R.id.password));

        Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                db = openOrCreateDatabase("credentials.sqlite", MODE_PRIVATE, null);
                Cursor c = db.query("user",
                        new String[]{"username", "password"},
                        "username = ? and password = ?",
                        new String[]{username.getText().toString(), password.getText().toString()},
                        null,
                        null,
                        null);

                if(c.getCount() == 1)
                {
                    Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), R.string.error_main, Toast.LENGTH_LONG).show();
                }
                db.close();
            }
        });


    }
}