package com.example.io.yaoban;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IO on 2018/1/10.
 */

public class GoodTuiJianActivity extends Activity {
    int[] iconId={
            R.id.homePageIcon,
            R.id.fenleiIcon,
            R.id.cartIcon,
            R.id.userInfoIcon
    };
    int[] goodsImg={
            R.drawable.biyun_medicine1,R.drawable.biyan_medicine2,R.drawable.biyan_medicine3,R.drawable.biyun_medicine4,R.drawable.biyun_medicine5
    };
    String[] goodsDesc={
            "优思明 屈螺酮炔雌醇片 21片",
            "妈富隆 去氧孕烯炔雌醇片 21片",
            "达英35 炔雌醇环丙孕酮片 21片",
            "欣妈富隆 去氧孕烯炔雌醇片 21片",
            "优思明  屈螺酮炔雌醇片 21片"
    };
    String[] goodsTags={
            "进口 西药",
            "进口 西药",
            "进口 西药 原研药",
            "进口 西药",
            "进口 西药 原研药"
    };
    ListView listView;
    int clickPosition;
    String[] goodsPrice={
        "￥108.00", "￥31.00","￥52.00","￥56.00","￥93.80"
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_tuijian);
        //设置icons：
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont/iconfont.ttf");
        for(int i=0;i<iconId.length;i++){
            TextView myicon= (TextView) findViewById(iconId[i]);
            myicon.setTypeface(iconfont);

        }
//        设置listView:
        listView= (ListView) findViewById(R.id.listView);
        List<Map<String,Object>> listItems=new ArrayList<Map<String, Object>>();
        for(int i=0;i<goodsImg.length;i++){
            Map<String,Object> item= new HashMap<String,Object>();
            item.put("goodsImg",goodsImg[i]);
            item.put("goodsDesc",goodsDesc[i]);
            item.put("goodsTags",goodsTags[i]);
            item.put("goodsPrice",goodsPrice[i]);
            listItems.add(item);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this, listItems, R.layout.listview_goods_item, new String[]{"goodsImg","goodsDesc","goodsTags","goodsPrice"},new int[]{R.id.goodsImg,R.id.goodsDesc,R.id.goodsTag,R.id.goodsPrice});
        listView.setAdapter(simpleAdapter);
//        设置listView的高度
        new ListViewUtils().setListViewHeightBasedOnChildren(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickPosition = position;
            }
        });
        View view= LayoutInflater.from(this).inflate(R.layout.listview_goods_item,null);
        ImageView imageView= (ImageView) view.findViewById(R.id.goodsCart);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(v);
            }
        });
    }
    //设置跳转到首页：
    public void goToHomePage(View view){
        Intent intent = new Intent();
        intent.setClass(GoodTuiJianActivity.this, HomePage.class);
        startActivity(intent);

    }
    //设置跳转到分类：
    public void goToFenLei(View  view){
        Intent intent = new Intent();
        intent.setClass(GoodTuiJianActivity.this, FenLeiActivity.class);
        startActivity(intent);

    }
    //设置跳转到购物车：
    public void goToCart(View  view){
        Intent intent = new Intent();
        intent.setClass(GoodTuiJianActivity.this, CartActivity.class);
        startActivity(intent);

    }
    //设置跳转到我的：
    public void goToUserCenter(View  view){
        Intent intent = new Intent();
        intent.setClass(GoodTuiJianActivity.this, UserCenterActivity.class);
        startActivity(intent);

    }

    //    点击添加到购物车：
    public void addToCart(View view){
            String priceStr=goodsPrice[clickPosition].substring(1,goodsPrice[clickPosition].length());
            float price=Float.parseFloat(priceStr);
            Intent intent = new Intent();
            intent.setClass(this, CartActivity.class);
            // 通过Bundle
            Bundle bundle = new Bundle();
            bundle.putString("goodsName",goodsDesc[clickPosition]);
            bundle.putString("goodsTags",goodsTags[clickPosition]);
            bundle.putInt("goodsImgId",goodsImg[clickPosition]);
            bundle.putFloat("goodsPrice",price);
            intent.putExtras(bundle);
            startActivity(intent);

    }

}
