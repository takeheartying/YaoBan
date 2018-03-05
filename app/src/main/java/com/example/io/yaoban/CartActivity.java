package com.example.io.yaoban;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class CartActivity extends Activity implements View.OnClickListener
        , CheckInterface, ModifyCountInterface {
        public TextView tv_title, tv_settlement, tv_show_price;
        private TextView tv_all_check;
        private CheckBox ck_all;
        private ListView list_shopping_cart;
        private ShoppingCartAdapter shoppingCartAdapter;
        private TextView tv_edit;
        private boolean flag = false;
        private List<ShoppingCartBean> shoppingCartBeanList = new ArrayList<>();
        private boolean mSelect;
        private double totalPrice = 0.00;// 购买的商品总价
        private int totalCount = 0;// 购买的商品总数量
        /**
         * 批量模式下，用来记录当前选中状态
         */
        private SparseArray<Boolean> mSelectState = new SparseArray<Boolean>();
        @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        initData();
        list_shopping_cart= (ListView) findViewById(R.id.list_shopping_cart);
        ck_all= (CheckBox) findViewById(R.id.ck_all);
        ck_all.setOnClickListener(this);
        tv_show_price= (TextView) findViewById(R.id.tv_show_price);
        tv_settlement= (TextView) findViewById(R.id.tv_settlement);
        tv_settlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this,"购买成功！",Toast.LENGTH_SHORT).show();
                shoppingCartBeanList=null;
            }
        });
        tv_edit= (TextView) findViewById(R.id.tv_edit);
        shoppingCartAdapter = new ShoppingCartAdapter(this);
        shoppingCartAdapter.setCheckInterface(this);
        shoppingCartAdapter.setModifyCountInterface(this);
        list_shopping_cart.setAdapter(shoppingCartAdapter);
        shoppingCartAdapter.setShoppingCartBeanList(shoppingCartBeanList);
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this,tv_edit.getText().toString(),Toast.LENGTH_SHORT).show();
                switch (tv_edit.getText().toString()){
                    case "编辑":{
                        shoppingCartAdapter.isShow(false);
                        tv_edit.setText("完成");
                        break;
                    }
                    case "完成":{
                        shoppingCartAdapter.isShow(true);
                        tv_edit.setText("编辑");
                        break;
                    }
                    default:break;

                }
            }
        });

//            显示添加的购物车：
            Bundle bundle=this.getIntent().getExtras();
            System.out.print("---------------------------------------------------------bundle="+bundle);
            if(bundle!=null){
                String goodsName=bundle.getString("goodsName");
                float goodsPrice=bundle.getFloat("goodsPrice");
                String goodsTags=bundle.getString("goodsTags");
                int goodsImgId=bundle.getInt("goodsImgId");
                ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
                shoppingCartBean.setGoodsImgId(goodsImgId);
                shoppingCartBean.setgoodsDesc(goodsName);
                shoppingCartBean.setGoodsTags(goodsTags);
                shoppingCartBean.setGoodsPrice(goodsPrice);
                shoppingCartBean.setGoodsCount(1);
                shoppingCartBeanList.add(shoppingCartBean);
            }

        }
        protected void initData() {
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
                float[] goodsPrice={
                        108,31,52,56,93
                };
                int[] goodsCount={1,4,2,1,4};
                for (int i = 0; i < goodsImg.length; i++) {
                ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
                shoppingCartBean.setGoodsImgId(goodsImg[i]);
                shoppingCartBean.setgoodsDesc(goodsDesc[i]);
                shoppingCartBean.setGoodsTags(goodsTags[i]);
                shoppingCartBean.setGoodsPrice(goodsPrice[i]);
                shoppingCartBean.setGoodsCount(goodsCount[i]);
                shoppingCartBeanList.add(shoppingCartBean);
                }

                }

        @Override
        public void onClick(View v) {
                switch (v.getId()) {
                //全选按钮
                case R.id.ck_all:
                if (shoppingCartBeanList.size() != 0) {
                if (ck_all.isChecked()) {
                for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                shoppingCartBeanList.get(i).setChoosed(true);
                }
                shoppingCartAdapter.notifyDataSetChanged();
                } else {
                for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                shoppingCartBeanList.get(i).setChoosed(false);
                }
                shoppingCartAdapter.notifyDataSetChanged();
                }
                }
                statistics();
                break;
                case R.id.tv_edit:
                flag = !flag;
                if (flag) {
                tv_edit.setText("完成");
                shoppingCartAdapter.isShow(false);
                } else {
                tv_edit.setText("编辑");
                shoppingCartAdapter.isShow(true);
                }
                break;
                }
                }

        /**
         * 单选
         *
         * @param position  组元素位置
         * @param isChecked 组元素选中与否
         */
        @Override
        public void checkGroup(int position, boolean isChecked) {

                shoppingCartBeanList.get(position).setChoosed(isChecked);

                if (isAllCheck())
                ck_all.setChecked(true);
                else
                ck_all.setChecked(false);

                shoppingCartAdapter.notifyDataSetChanged();
                statistics();
                }


        /**
         * 遍历list集合
         *
         * @return
         */
        private boolean isAllCheck() {

                for (ShoppingCartBean group : shoppingCartBeanList) {
                if (!group.isChoosed())
                return false;
                }
                return true;
                }

        /**
         * 统计操作
         * 1.先清空全局计数器<br>
         * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
         * 3.给底部的textView进行数据填充
         */
        public void statistics() {
                totalCount = 0;
                totalPrice = 0.00;
                for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(i);
                if (shoppingCartBean.isChoosed()) {
                totalCount++;
                totalPrice += shoppingCartBean.getGoodsPrice() * shoppingCartBean.getGoodsCount();
                }
                }
                tv_show_price.setText("合计:" + totalPrice);
                tv_settlement.setText("结算(" + totalCount + ")");
                }

        /**
         * 增加
         *
         * @param position      组元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        @Override
        public void doIncrease(int position, View showCountView, boolean isChecked) {
                ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
                int currentCount = shoppingCartBean.getGoodsCount();
                currentCount++;
                shoppingCartBean.setGoodsCount(currentCount);
                ((TextView) showCountView).setText(currentCount + "");
                shoppingCartAdapter.notifyDataSetChanged();
                statistics();
                }

        /**
         * 删减
         *
         * @param position      组元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        @Override
        public void doDecrease(int position, View showCountView, boolean isChecked) {
                ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
                int currentCount = shoppingCartBean.getGoodsCount();
                if (currentCount == 1) {
                return;
                }
                currentCount--;
                shoppingCartBean.setGoodsCount(currentCount);
                ((TextView) showCountView).setText(currentCount + "");
                shoppingCartAdapter.notifyDataSetChanged();
                statistics();

                }

        /**
         * 删除
         *
         * @param position
         */
        @Override
        public void childDelete(int position) {
                shoppingCartBeanList.remove(position);
                shoppingCartAdapter.notifyDataSetChanged();
                statistics();

                }
}