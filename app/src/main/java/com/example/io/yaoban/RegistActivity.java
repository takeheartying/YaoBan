package com.example.io.yaoban;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistActivity extends Activity implements View.OnClickListener{
    private Button register;
    private Button back;
    private String userName;
    private String password;
    private EditText editUser;
    private EditText pwd;
    private EditText pwdAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        editUser=(EditText)findViewById(R.id.reg_userText);
        pwd=(EditText)findViewById(R.id.reg_pwdText);
        pwdAgain=(EditText)findViewById(R.id.reg_pwdagText);
        register= (Button) findViewById(R.id.registBtn);
        register.setTag(1);
        register.setOnClickListener(this);
        back=(Button)findViewById(R.id.backBtn);
        back.setTag(2);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int Tag=(int)v.getTag();
        switch (Tag){
            case 1:
                Toast.makeText(RegistActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(this,HomePage.class);
                startActivity(intent);
                break;
            case 2:
                Intent intent2=new Intent(this,LoginAndRegist.class);
                startActivity(intent2);
                break;
            default:
                Toast.makeText(RegistActivity.this,"注册失败，请再试一次",Toast.LENGTH_LONG).show();
                break;

        }

    }



    public boolean pwdCorrect(){
        if(pwd.getText().toString().equals(pwdAgain.getText().toString())){
            return true;
        }
        Toast.makeText(RegistActivity.this,"两次密码输入不一致，请重新输入。",Toast.LENGTH_LONG).show();
        return false;


    }
}


