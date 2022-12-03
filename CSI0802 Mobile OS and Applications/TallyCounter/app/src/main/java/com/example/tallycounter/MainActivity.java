package com.example.tallycounter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();

        cnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = Integer.toString(Integer.parseInt(txtv.getText().toString()) + 1);
                txtv.setText(a);

                int counter = Integer.parseInt(txtv.getText().toString());
                String c = txtv2.getText().toString();
                int counter2 = Integer.parseInt(c);

                if((counter)%7==0){
                    c = Integer.toString(counter2+1);
                }

                txtv2.setText(c);
            }
        });

        rst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b = "0";
                txtv.setText(b);
                txtv2.setText(b);
            }
        });

        svd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        loadData();
        updateViews();
    }

    private TextView txtv, txtv2;
    private Button cnt;
    private Button rst;
    private Button svd;

    public void setUI() {
        txtv = findViewById(R.id.text1);
        cnt = findViewById(R.id.button);
        rst = findViewById(R.id.button2);
        txtv2 = findViewById(R.id.text5);
        svd = findViewById(R.id.button3);
    }

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    private String text;
    public void saveData(){
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(TEXT,txtv2.getText().toString());

        editor.apply();
        Toast.makeText(this,"Data Saved",Toast.LENGTH_SHORT).show();
    }
    public void loadData(){
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

        text = sp.getString(TEXT, "");
    }
    public void updateViews(){
        txtv2.setText(text);

    }
}