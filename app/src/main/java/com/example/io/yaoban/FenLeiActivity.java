package com.example.io.yaoban;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class FenLeiActivity  extends Activity implements AdapterView.OnItemClickListener {

    private String[] strs = { "热门分类", "妇科用药", "男科用药", "神经用药", "心脑用药", "肝胆用药", "滋补保健",
            "皮肤用药", "五官用药" };
    private ListView listView;
    private MyAdapter adapter;
    private MyFragment myFragment;
    private MyFragment1 myFragment1;
    public static int mPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fen_lei);
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        // TODO Auto-generated method stub
        listView = (ListView) findViewById(R.id.listview);

        adapter = new MyAdapter(this, strs);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        //创建MyFragment对象
        myFragment = new MyFragment();
        android.app.FragmentManager manager=getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, myFragment);
        //通过bundle传值给MyFragment
        Bundle bundle = new Bundle();
        bundle.putString(MyFragment.TAG, strs[mPosition]);
        myFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        //拿到当前位置
        mPosition = position;
        //即使刷新adapter
        adapter.notifyDataSetChanged();

            if(position==1){
                myFragment1 = new MyFragment1();
                android.app.FragmentManager manager=getFragmentManager();
                android.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, myFragment1);
                Bundle bundle = new Bundle();
                bundle.putString(MyFragment1.TAG, strs[position]);
                myFragment1.setArguments(bundle);
                fragmentTransaction.commit();
            }else {
                myFragment = new MyFragment();
                android.app.FragmentManager manager=getFragmentManager();
                android.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, myFragment);
                Bundle bundle = new Bundle();
                bundle.putString(MyFragment.TAG, strs[position]);
                myFragment.setArguments(bundle);
                fragmentTransaction.commit();
            }


    }
}
