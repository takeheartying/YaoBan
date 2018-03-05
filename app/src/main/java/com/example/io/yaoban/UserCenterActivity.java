package com.example.io.yaoban;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IO on 2018/1/10.
 */

public class UserCenterActivity extends Activity {
    int[] iconId={
            R.drawable.wodeshoucang,
            R.drawable.wodezuji,
            R.drawable.sirendingzhi,
            R.drawable.dizhiguanli,
            R.drawable.zhanghaoguanli,
            R.drawable.zhengjianguanli,
            R.drawable.bangzhufankui,
            R.drawable.zaixiankefu
    };
    String[] iconName={
            "我的收藏","我的足迹","私人订制","地址管理","账号管理","证件管理","帮助反馈","在线客服"
    };
    int[] menuIcon={
            R.id.tuijianIcon,
            R.id.fenleiIcon,
            R.id.cartIcon,
            R.id.homePageIcon
    };
    GridView gridView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_center);
        //设置icons：
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont/iconfont.ttf");
        for(int i=0;i<menuIcon.length;i++){
            TextView myicon= (TextView) findViewById(menuIcon[i]);
            myicon.setTypeface(iconfont);

        }
        gridView= (GridView) findViewById(R.id.gridView);
        List<Map<String ,Object>> listItems=new ArrayList<Map<String ,Object>>();
        for(int i=0;i<iconId.length;i++){
            Map<String ,Object> item=new HashMap<String ,Object>();
            item.put("icon",iconId[i]);
            item.put("iconName",iconName[i]);
            listItems.add(item);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(UserCenterActivity.this,listItems,R.layout.gridview_icon,new String[]{"icon","iconName"},new int[]{R.id.icon,R.id.iconName});
        gridView.setAdapter(simpleAdapter);
    }
    //设置跳转到首页：
    public void goToHomePage(View view){
        Intent intent = new Intent();
        intent.setClass(UserCenterActivity.this, HomePage.class);
        startActivity(intent);

    }
    //设置跳转到分类：
    public void goToFenLei(View  view){
        Intent intent = new Intent();
        intent.setClass(UserCenterActivity.this, FenLeiActivity.class);
        startActivity(intent);

    }
    //设置跳转到购物车：
    public void goToCart(View  view){
        Intent intent = new Intent();
        intent.setClass(UserCenterActivity.this, CartActivity.class);
        startActivity(intent);

    }
    //设置跳转到推荐：
    public void goToGoodTuijian(View  view){
        Intent intent = new Intent();
        intent.setClass(UserCenterActivity.this, GoodTuiJianActivity.class);
        startActivity(intent);

    }
}
