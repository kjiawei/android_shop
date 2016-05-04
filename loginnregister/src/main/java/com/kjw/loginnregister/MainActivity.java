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
    private EditText editText1=null;//输入用户名（手机或邮箱）
    private EditText editText2=null;//输入密码
    private EditText editText3=null;//输入订单编号
    private String DocNum;//订单编号
    private Button button1=null;
    private Button button2=null;
    private Button button3=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE); //去除标题
        //getWindow().setFlags(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);  //设置全屏

        setContentView(R.layout.activity_main);
        //进阶：依赖注入 注解【xUtils，afinal类】  更优--》ButterKnife
        //@ContentView(value = R.layout.activity_main)
        //public class MainActivity extends BaseActivity
        //{
	      //  @ViewInject(R.id.id_btn)
	      //  private Button mBtn1;
	      //  @ViewInject(R.id.id_btn02)
	      //  private Button mBtn2;
       // }
        editText1 = (EditText)findViewById(R.id.userName);
        editText2 = (EditText)findViewById(R.id.userPhone);
        editText3 = (EditText)findViewById(R.id.docNum);

        button1 = (Button)findViewById(R.id.loginButton);
        button2 = (Button)findViewById(R.id.registerButton);
        button3 = (Button)findViewById(R.id.orderSearch);

        button1.setOnClickListener(new myButtonListener1());//登录
        button2.setOnClickListener(new myButtonListener2());//注册
        button3.setOnClickListener(new myButtonListener3());//确认查询
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
    
    class myButtonListener3 implements View.OnClickListener {
        public void onClick(View v){
                //得到用户输入的订单编号
                docNum = editText3.getText().toString();
                //将订单编号传到URL

                myThread mythread = new myThread();

                //跳转至订单显示页面
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, OrderMain.class);
                startActivity(intent);
            }
    }
    
    class myThread extends Thread{
        public void run(){
            //第一步，创建post对象
            HttpPost httpPost = new HttpPost(url);

            //设置HTTP POST请求参数必须用NameValuePair对象
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("docNum",docNum));

            HttpResponse httpResponse = null;
            try{
                //设置httpPost请求参数
                httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                httpResponse = new DefaultHttpClient().execute(httpPost);
                if(httpResponse.getStatusLine().getStatusCode()==200){
                    // 第三步，使用getEntity方法获得返回结果
                    String result = EntityUtils.toString(httpResponse.getEntity());
                    Log.i(result,"123");
                }
            }catch(ClientProtocolException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
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
