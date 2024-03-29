package com.example.middle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class SwitchIOSActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private CheckBox ck_status;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_ios);
        ck_status = findViewById(R.id.ck_status);
        tv_result = findViewById(R.id.tv_result);
        ck_status.setOnCheckedChangeListener(this);
        refreshResult();
    }

    private void refreshResult() {
        String result = String.format("仿IOS开关的状态是%s",
                (ck_status.isChecked()) ? "开" : "关");
        tv_result.setText(result);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        refreshResult();
    }
}
