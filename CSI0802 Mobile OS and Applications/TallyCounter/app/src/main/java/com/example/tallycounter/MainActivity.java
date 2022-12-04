package com.example.tallycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public void fnc(View v){
        int qt = Integer.parseInt(txtv.getText().toString())+1;
        String a = Integer.toString(qt);
        txtv.setText(a);

        int counter = Integer.parseInt(txtv.getText().toString());
        String c = txtv2.getText().toString();
        int counter2 = Integer.parseInt(c);

        if((counter)%108==0){
            c = Integer.toString(counter2+1);
        }

        txtv2.setText(c);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();

        rst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b = "0";
                txtv.setText(b);
                txtv2.setText(b);
            }
        });

        loadData();
        updateViews();
    }

    private TextView txtv, txtv2;
    private Button rst;

    public void setUI() {
        txtv = findViewById(R.id.text1);
        rst = findViewById(R.id.button2);
        txtv2 = findViewById(R.id.text5);
    }

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private String text;

    public void saveData(View v){
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




    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if(event.getAction()==KeyEvent.ACTION_UP) {
                    fnc(null);
                }
                break;
            default:
                super.dispatchKeyEvent(event);
                break;
        }

        return true;
    }


}