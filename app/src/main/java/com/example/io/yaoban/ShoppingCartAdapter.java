package com.example.io.yaoban;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public  class ShoppingCartAdapter extends BaseAdapter {

    private boolean isShow = true;//是否显示编辑/完成
    private List<ShoppingCartBean> shoppingCartBeanList;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    private Context context;

    public ShoppingCartAdapter(Context context) {
        this.context = context;
    }

    public void setShoppingCartBeanList(List<ShoppingCartBean> shoppingCartBeanList) {
        this.shoppingCartBeanList = shoppingCartBeanList;
        notifyDataSetChanged();
    }

    /**
     * 单选接口
     *
     * @param checkInterface
     */
    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    /**
     * 改变商品数量接口
     *
     * @param modifyCountInterface
     */
    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getCount() {
        return shoppingCartBeanList == null ? 0 : shoppingCartBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return shoppingCartBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * 是否显示可编辑
     *
     * @param flag
     */
    public void isShow(boolean flag) {
        isShow = flag;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_cart_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        holder.tv_goodsDesc.setText(shoppingCartBean.getgoodsDesc());
        holder.tv_tag.setText(shoppingCartBean.getGoodsTags());
        holder.tv_price.setText("￥" + shoppingCartBean.getGoodsPrice());
        holder.ck_chose.setChecked(shoppingCartBean.isChoosed());
        holder.tv_show_num.setText(shoppingCartBean.getGoodsCount() + "");
        holder.tv_num.setText("X" + shoppingCartBean.getGoodsCount());
        holder.iv_show_pic.setImageResource(shoppingCartBean.getGoodsImgId());
        holder.iv_add.setImageResource(R.drawable.plus);
        holder.iv_sub.setImageResource(R.drawable.sub);

        //单选框按钮
        holder.ck_chose.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shoppingCartBean.setChoosed(((CheckBox) v).isChecked());
                        checkInterface.checkGroup(position, ((CheckBox) v).isChecked());//向外暴露接口
                    }
                }
        );

        //增加按钮
        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doIncrease(position, holder.tv_show_num, holder.ck_chose.isChecked());//暴露增加接口
            }
        });

        //删减按钮
        holder.iv_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doDecrease(position, holder.tv_show_num, holder.ck_chose.isChecked());//暴露删减接口
            }
        });


        //删除弹窗
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(context).create();
                alert.setTitle("操作提示");
                alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                modifyCountInterface.childDelete(position);//删除 目前只是从item中移除

                            }
                        });
                alert.show();
            }
        });

        //判断是否在编辑状态下
        if (isShow) {
            holder.tv_goodsDesc.setVisibility(View.VISIBLE);
            holder.tv_tag.setVisibility(View.VISIBLE);
            holder.rl_edit.setVisibility(View.GONE);
        } else {
            holder.tv_goodsDesc.setVisibility(View.GONE);
            holder.tv_tag.setVisibility(View.GONE);
            holder.rl_edit.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}
//初始化控件
class ViewHolder {
    ImageView iv_show_pic, iv_sub, iv_add;
    TextView tv_goodsDesc,tv_tag, tv_price, tv_num, tv_delete, tv_show_num;
    CheckBox ck_chose;
    RelativeLayout rl_edit;

    public ViewHolder(View itemView) {
        ck_chose = (CheckBox) itemView.findViewById(R.id.ck_chose);
        iv_show_pic = (ImageView) itemView.findViewById(R.id.iv_show_pic);
        iv_sub = (ImageView) itemView.findViewById(R.id.iv_sub);
        iv_add = (ImageView) itemView.findViewById(R.id.iv_add);
        tv_goodsDesc = (TextView) itemView.findViewById(R.id.tv_goodsDesc);
        tv_tag = (TextView) itemView.findViewById(R.id.tv_tag);
        tv_price = (TextView) itemView.findViewById(R.id.tv_price);
        tv_num = (TextView) itemView.findViewById(R.id.tv_num);
        tv_delete = (TextView) itemView.findViewById(R.id.tv_delete);
        tv_show_num = (TextView) itemView.findViewById(R.id.tv_show_num);
        rl_edit = (RelativeLayout) itemView.findViewById(R.id.rl_edit);

    }
}
