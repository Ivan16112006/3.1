package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button click;
    private TextView number;
    private SharedPreferences pref;
    private final String save_key = "save_key";


    int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click = (Button) findViewById(R.id.button);
        number = (TextView) findViewById(R.id.textView);
        pref = getSharedPreferences("Test", MODE_PRIVATE);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                number.setText(String.valueOf(num));

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(save_key, number.getText().toString());
        edit.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        number.setText(pref.getString(save_key, "ooo"));
        num = Integer.parseInt(pref.getString(save_key, "ooo"));
    }
}