package com.example.io.yaoban;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private String[] strings;
    public static int mPosition;

    public MyAdapter(Context context, String[] strings){
        this.context =context;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, null);
        TextView tv = (TextView) convertView.findViewById(R.id.tv);
        mPosition = position;
        tv.setText(strings[position]);
        if (position == FenLeiActivity.mPosition) {
            convertView.setBackgroundResource(R.drawable.listview_item_bgd);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#f4f4f4"));
        }
        return convertView;
    }
}