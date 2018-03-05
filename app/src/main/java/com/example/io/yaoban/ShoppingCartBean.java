package com.example.io.yaoban;


public class ShoppingCartBean {
    public String goodsDesc;
    public String goodsTags;
    public float goodsPrice;
    public int goodsCount;
    public boolean isChoosed;
    public int goodsImgId;

    public int getGoodsImgId() {
        return goodsImgId;
    }

    public void setGoodsImgId(int goodsImgId) {
        this.goodsImgId = goodsImgId;
    }

    public String getgoodsDesc() {
        return goodsDesc;
    }

    public void setgoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsTags() {
        return goodsTags;
    }

    public void setGoodsTags(String goodsTags) {
        this.goodsTags = goodsTags;
    }

    public float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }
}
