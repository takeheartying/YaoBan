package com.example.io.yaoban;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MyFragment1 extends Fragment {
    public Context context;
    public static final String TAG = "MyFragment1";
    private String str;
    public int[] iconId={
            R.drawable.biyun,
            R.drawable.yindaoyan,
            R.drawable.penqingyan,
            R.drawable.gongjingyan,
            R.drawable.tongjing,
            R.drawable.zigongjibing,
            R.drawable.ruxianjibing,
            R.drawable.baotaicuyun,
            R.drawable.gengnianqi,
            R.drawable.chanhouyongyao
    };
    String[] iconName={
            "避孕","阴道炎","盆腔炎","宫颈炎","痛经","子宫疾病","乳腺疾病","保胎促孕","更年期","产后用药"
    };
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.myfragment, null);
        TextView tv_title = (TextView)view.findViewById(R.id.tv_title);
        //得到数据
        str = getArguments().getString(TAG);
        tv_title.setText(str);
        GridView gridView= (GridView) view.findViewById(R.id.gridView);
        List<Map<String ,Object>> listItems=new ArrayList<Map<String ,Object>>();
        for(int i=0;i<iconId.length;i++){
            Map<String ,Object> item=new HashMap<String ,Object>();
            item.put("icon",iconId[i]);
            item.put("iconName",iconName[i]);
            listItems.add(item);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(getActivity(),listItems,R.layout.gridview_icon,new String[]{"icon","iconName"},new int[]{R.id.icon,R.id.iconName});
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    //设置跳转到推荐：
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), GoodTuiJianActivity.class);
                        startActivity(intent);
                }
            }
        });
        return view;
    }
}