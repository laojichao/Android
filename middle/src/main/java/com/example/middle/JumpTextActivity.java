package com.example.middle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JumpTextActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_text);
        EditText et_username = findViewById(R.id.et_username);
        EditText et_password = findViewById(R.id.et_password);
        Button btn_login = findViewById(R.id.btn_login);
        et_username.addTextChangedListener(new JumpTextWatcher(et_username, et_password));
        et_password.addTextChangedListener((new JumpTextWatcher(et_password, btn_login)));
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            Toast.makeText(this, "这个登录按钮啥事也没做", Toast.LENGTH_SHORT).show();
        }
    }

    //定义一个编辑框监听器，在输入回车符时自动跳动到下一个控件
    private class JumpTextWatcher implements TextWatcher {
        private EditText mThisView;
        private View mNextView;

        /*
        现在所在控件，下一控件
        * */
        public JumpTextWatcher(EditText vThis, View vNext) {
            super();
            mThisView = vThis;
            if (vNext != null) {
                mNextView = vNext;
            }
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String str = s.toString();
            if (str.contains("\r") || str.contains("\n")) {
                //去掉回车符合换行符
                mThisView.setText(str.replace("\r", "").replace("\n", ""));
                if (mNextView != null) {
                    //让下一个视图获得焦点，即将光标移到下个视图
                    mNextView.requestFocus();
                    //如果下一代视图是已编辑，即将光标自动移到编辑的文本末尾
                    if (mNextView instanceof  EditText) {
                        EditText et = (EditText) mNextView;
                        //让光标自动移到编辑框内部的文本末尾
                        //方式一：直接调用EditText的setSelection
                        et.setSelection(et.getText().length());
                        //方式二：调用Selection类的setSelection方法
                        //Editable edit = et.getText();
                    }
                }
            }
        }
    }
}
