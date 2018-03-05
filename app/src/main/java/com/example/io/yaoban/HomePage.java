package com.example.io.yaoban;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.zhouwei.mzbanner.*;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by IO on 2018/1/9.
 */

public class HomePage extends Activity {
    MZBannerView mMZBanner;
    public static final int []RES = new int[]{R.drawable.banner1,R.drawable.banner2,R.drawable.banner3,R.drawable.banner4,R.drawable.banner5,R.drawable.banner6,};
    int[] iconId={
            R.id.icon1,
            R.id.icon2,
            R.id.icon3,
            R.id.icon4,
            R.id.icon5,
            R.id.icon6,
            R.id.icon7,
            R.id.icon8,
            R.id.icon9,
            R.id.icon10,
            R.id.tuijianIcon,
            R.id.fenleiIcon,
            R.id.cartIcon,
            R.id.userInfoIcon
    };
//    private String[] icon = {"&#xe6a5;","&#xe676;","&#xec52;","&#xe6c1;","&#xe606;","&#xe600;","&#xe614;","&#xe611;","&#xe612;","&#xe6ae;"};
//    private String[] iconName = { "男性健康", "幼妇保健", "营养滋补", "健康视频", "病毒防治", "慢病管家", "疾病诊断",
//             "病情简介", "轻松找药", "成人用品" };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
// 设置bannner:
        mMZBanner = (MZBannerView) findViewById(R.id.banner);
        // 设置数据
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<RES.length;i++){
            list.add(RES[i]);
        }
        mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
//设置icons：
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont/iconfont.ttf");
        for(int i=0;i<iconId.length;i++){
            TextView myicon= (TextView) findViewById(iconId[i]);
            myicon.setTypeface(iconfont);

        }
    }
    //设置跳转到具体详情页：
    public void goToMedicineDetails(View  view){
        Intent intent = new Intent();
        intent.setClass(HomePage.this, GoodsDetails.class);
        startActivity(intent);

    }
    //设置跳转到推荐：
    public void goToGoodTuijian(View  view){
        Intent intent = new Intent();
        intent.setClass(HomePage.this, GoodTuiJianActivity.class);
        startActivity(intent);
    }
    //设置跳转到分类：
    public void goToFenLei(View  view){
        Intent intent = new Intent();
        intent.setClass(HomePage.this, FenLeiActivity.class);
        startActivity(intent);

    }
    //设置跳转到购物车：
    public void goToCart(View  view){
        Intent intent = new Intent();
        intent.setClass(HomePage.this, CartActivity.class);
        startActivity(intent);

    }
    //设置跳转到我的：
    public void goToUserCenter(View  view){
        Intent intent = new Intent();
        intent.setClass(HomePage.this, UserCenterActivity.class);
        startActivity(intent);

    }
    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();//开始轮播
    }
    public static class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);
        }
    }
}


