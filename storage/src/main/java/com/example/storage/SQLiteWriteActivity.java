package com.example.storage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.storage.bean.UserInfo;
import com.example.storage.database.UserDBHelper;
import com.example.storage.util.DateUtil;
import com.example.storage.util.Utils;
import com.example.storage.util.ViewUtil;

import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SQLiteWriteActivity extends AppCompatActivity implements OnClickListener{
    private UserDBHelper mHelper;       //用户数据库帮助器对象
    private EditText et_name;
    private EditText et_age;
    private EditText et_height;
    private EditText et_weight;
    private boolean bMarried = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_write);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        findViewById(R.id.btn_save).setOnClickListener(this);
        initTypeSpinner();
    }

    private String typeArray[] = {"未婚", "已婚"};

    private void initTypeSpinner() {
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, typeArray);
        typeAdapter.setDropDownViewResource(R.layout.item_dropdown);
        Spinner sp_marreid = findViewById(R.id.sp_married);
        sp_marreid.setPrompt("请选择婚姻状况");
        sp_marreid.setAdapter(typeAdapter);
        sp_marreid.setSelection(0);
        sp_marreid.setOnItemSelectedListener(new TypeSelectedListener());
    }

    class TypeSelectedListener implements OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            bMarried = (position == 0) ? false : true ;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //获得数据库帮助器实例
        mHelper = UserDBHelper.getInstance(this, 2);
        //打开数据库帮助器的写连接
        mHelper.openWriteLink();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //关闭数据库连接
        mHelper.closeLink();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
            String name = et_name.getText().toString();
            String age = et_age.getText().toString();
            String height = et_height.getText().toString();
            String weight = et_weight.getText().toString();
            if (TextUtils.isEmpty(name)) {
                showToast("请先填写姓名");
                return;
            } else if (TextUtils.isEmpty(age)) {
                showToast("请先填写名字");
                return;
            } else if (TextUtils.isEmpty(height)) {
                showToast("请=先填写身高");
                return;
            } else if (TextUtils.isEmpty(weight)) {
                showToast("请先填写体重");
                return;
            }
            //以下声明一个用户信息对象，并并填写它各字段的值
            UserInfo info = new UserInfo();
            info.name = name;
            info.age = Integer.parseInt(age);
            info.height = Long.parseLong(height);
            info.weight = Float.parseFloat(weight);
            info.married = bMarried;
            info.update_time = DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss");
            //执行数据库帮助器的插入操作
            mHelper.insert(info);
            showToast("数据已写入SQLite数据库");

        }
    }

    private void showToast(String desc) {
        Toast.makeText(this, desc, Toast.LENGTH_SHORT).show();
    }
}
