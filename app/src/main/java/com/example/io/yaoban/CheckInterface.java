package com.example.io.yaoban;

import android.view.View;

/**
 * 复选框接口
 */
interface CheckInterface {
    /**
     * 组选框状态改变触发的事件
     *
     * @param position  元素位置
     * @param isChecked 元素选中与否
     */
    void checkGroup(int position, boolean isChecked);
}


/**
 * 改变数量的接口
 */
interface ModifyCountInterface {
    /**
     * 增加操作
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    void doIncrease(int position, View showCountView, boolean isChecked);

    /**
     * 删减操作
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    void doDecrease(int position, View showCountView, boolean isChecked);

    /**
     * 删除子item
     *
     * @param position
     */
    void childDelete(int position);
}