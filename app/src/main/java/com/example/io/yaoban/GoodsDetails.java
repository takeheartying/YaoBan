package com.example.io.yaoban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by IO on 2018/1/11.
 */

public class GoodsDetails extends Activity {
    TextView goodsName;
    ImageView imageView;
    TextView goodsPrice;
    TextView goodsTags;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_details);
        goodsName= (TextView) findViewById(R.id.goodsName);
        imageView= (ImageView) findViewById(R.id.goodsImg);
        goodsPrice= (TextView) findViewById(R.id.goodsPrice);
        goodsTags= (TextView) findViewById(R.id.goodsTag);
    }
//    点击添加到购物车：
    public void addToCart(View view){
        String priceStr=goodsPrice.getText().toString().substring(1, goodsPrice.getText().toString().length());
        System.out.println("___________________________________priceStr="+priceStr);
        float price=Float.parseFloat(priceStr);
        Intent intent = new Intent();
        intent.setClass(this, CartActivity.class);
// 通过Bundle
        Bundle bundle = new Bundle();
        bundle.putString("goodsName",goodsName.getText().toString());
        bundle.putString("goodsTags",goodsTags.getText().toString());
        bundle.putInt("goodsImgId",R.drawable.yiyu_medicine1);
        bundle.putFloat("goodsPrice",price);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
