package com.example.senior;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.senior.util.DateUtil;

public class VibratorActivity extends AppCompatActivity implements OnClickListener {
    private static TextView tv_vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrator);
        tv_vibrator = findViewById(R.id.tv_vibrator);
        findViewById(R.id.btn_start).setOnClickListener(this);
        initDurationSpinner();
    }

    public void initDurationSpinner() {
        ArrayAdapter<String> durationAdapter =new  ArrayAdapter<String>(this,
                R.layout.item_select, durationDescArray);
        Spinner sp_duration = findViewById(R.id.sp_duration);
        sp_duration.setPrompt("选择震动时长");
        sp_duration.setAdapter(durationAdapter);
        sp_duration.setOnItemSelectedListener(new  DurationSelectedListener());
        sp_duration.setSelection(0);
    }

    private int mDuration;  //震时长
    private int[] durationArray = {500, 1000, 2000, 3000, 4000, 5000};
    private String[] durationDescArray = {"0.5秒", "1秒", "2秒", "3秒", "4秒", "5秒"};

    class DurationSelectedListener implements OnItemSelectedListener {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mDuration = durationArray[position];
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start) {
            //熊系统服务中获取震动管理器
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            //命令震动器吱吱个若干秒
            vibrator.vibrate(mDuration);
            String desc = String.format("%s手机震动了%s秒", DateUtil.getNowTime(), mDuration / 1000.0f);
            tv_vibrator.setText(desc);
        }
    }
}
