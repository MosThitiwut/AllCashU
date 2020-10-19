package com.unitedfoods.allcashu.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.unitedfoods.allcashu.DB;
import com.unitedfoods.allcashu.R;
import com.unitedfoods.allcashu.SettingContract;

public class MainActivity extends AppCompatActivity {
    private static int SLASH_TIME_OUT = 4000;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB database = DB.getInstance(this);
        final SQLiteDatabase db = database.getReadableDatabase();



                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },SLASH_TIME_OUT);
            }




}
