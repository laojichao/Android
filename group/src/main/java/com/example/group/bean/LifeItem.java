package com.example.group.bean;

import com.example.group.R;

import java.util.ArrayList;

/**
 * LifeItem
 *
 * @author lao
 * @date 2019/5/11
 */
public class LifeItem {
    public int pic;
    public String title;

    public LifeItem(int pic, String title) {
        this.pic = pic;
        this.title = title;
    }

    public static ArrayList<LifeItem> getDefault() {
        ArrayList<LifeItem> itemArray = new ArrayList<LifeItem>();
        for (int i = 0; i < 40 ; i++) {
            itemArray.add(new LifeItem(R.drawable.icon_transfer, "转账"));
        }
        return itemArray;
    }
}
