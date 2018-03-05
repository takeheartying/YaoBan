package com.example.io.yaoban;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by IO on 2018/1/8.
 */

public class LoginAndRegist extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_regist);
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont/iconfont.ttf");
        TextView textview = (TextView)findViewById(R.id.logo);
        textview.setTypeface(iconfont);//设置icon的字体
    }
    public void login(View  view){
        Intent intent = new Intent();
        intent.setClass(LoginAndRegist.this, LoginActivity.class);
        startActivity(intent);
        LoginAndRegist.this.finish();
    }
    public void regist(View  view){
        Intent intent = new Intent();
        intent.setClass(LoginAndRegist.this, RegistActivity.class);
        startActivity(intent);
        LoginAndRegist.this.finish();
    }
}
