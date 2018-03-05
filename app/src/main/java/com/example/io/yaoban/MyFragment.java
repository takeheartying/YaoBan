package com.example.io.yaoban;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.io.yaoban.R;
public class MyFragment extends Fragment {
    public Context context;
    public static final String TAG = "MyFragment";
    private String str;
    public int[] iconId={
            R.drawable.ganmaokesou,
            R.drawable.biyan,
            R.drawable.kouqiangkuiyang,
            R.drawable.manxingyanyan,
            R.drawable.jixingchangyan,
            R.drawable.xiaohuabuliang,
            R.drawable.guanjieyan,
            R.drawable.pifusaoyang,
            R.drawable.tuofa,
            R.drawable.naogengse,
            R.drawable.shimian
    };
    String[] iconName={
            "感冒咳嗽","鼻炎","口腔溃疡","慢性咽炎","急性肠炎","消化不良","关节痛","皮肤瘙痒","脱发","脑梗塞","失眠"
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
        return view;
    }
}