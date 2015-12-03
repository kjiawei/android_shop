package com.kjw.loginnregister;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    private EditText editText1=null;
    private EditText editText2=null;
    private Button button1=null;
    private Button button2=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题
        //getWindow().setFlags(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);  //设置全屏

        setContentView(R.layout.activity_main);

        editText1 = (EditText)findViewById(R.id.userName);
        editText2 = (EditText)findViewById(R.id.userPhone);

        button1 = (Button)findViewById(R.id.loginButton);
        button2 = (Button)findViewById(R.id.registerButton);

        button1.setOnClickListener(new myButtonListener1());
        button2.setOnClickListener(new myButtonListener2());
    }

    class myButtonListener1 implements View.OnClickListener {
        boolean result = false;
        String name;
        String phone;
        public void onClick(View v){
            try{
                name = editText1.getText().toString();
                name = new String(name.getBytes("ISO8859-1"),"UTF-8");
                phone = editText2.getText().toString();
                phone = new String(name.getBytes("ISO8859-1"),"UTF-8");
            }catch(UnsupportedEncodingException e1){
               e1.printStackTrace();
            }
            try{
                result = NewService.save(name,phone);
            }catch(Exception e){
                e.printStackTrace();
            }
            if(result){
                Toast.makeText(MainActivity.this,R.string.ok, Toast.LENGTH_SHORT).show();

                //跳转至主订单页面
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,OrderPage.class);
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this,R.string.no, Toast.LENGTH_SHORT).show();
            }
        }
    }

    class myButtonListener2 implements View.OnClickListener {
        public void onClick(View v){
                //跳转至注册页面
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, registerPage.class);
                startActivity(intent);
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
