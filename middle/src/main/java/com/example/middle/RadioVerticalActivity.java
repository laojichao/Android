package com.example.middle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioVerticalActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private TextView tv_marry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_vertical);
        tv_marry = findViewById(R.id.tv_marry);
        RadioGroup rg_marry = findViewById(R.id.rg_marry);
        rg_marry.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_married) {
            tv_marry.setText("祝你早生贵子");
        } else if (checkedId == R.id.rb_unmarried) {
            tv_marry.setText("你的前途不可限量");
        }
    }
}
