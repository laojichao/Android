package com.example.group;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class TabGroupActivity extends ActivityGroup implements OnClickListener {
    private static final String TAG = "TabGroupActivity";
    private Bundle mBundle = new Bundle();
    private LinearLayout ll_container, ll_first, ll_second, ll_third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_group);
        ll_container = findViewById(R.id.ll_container);
        ll_first = findViewById(R.id.ll_first);
        ll_second = findViewById(R.id.ll_second);
        ll_third = findViewById(R.id.ll_third);
        ll_first.setOnClickListener(this);
        ll_second.setOnClickListener(this);
        ll_third.setOnClickListener(this);
        mBundle.putString("tag", TAG);
        changeContainerView(ll_first);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_first || v.getId() == R.id.ll_second || v.getId() == R.id.ll_third) {
            changeContainerView(v);
        }
    }

    private void changeContainerView(View v) {
        ll_first.setSelected(false);
        ll_second.setSelected(false);
        ll_third.setSelected(false);
        if (v == ll_first) {
            toActivity("first", TabFirstActivity.class);
        } else if (v == ll_second) {
            toActivity("second", TabSecondActivity.class);
        } else if (v == ll_third) {
            toActivity("third", TabThirdActivity.class);
        }
    }

    private void toActivity(String label, Class<?> cla) {
        Intent intent = new Intent(this, cla).putExtras(mBundle);
        ll_container.removeAllViews();
        View v = getLocalActivityManager().startActivity(label, intent).getDecorView();
        v.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        ll_container.addView(v);
    }

}
