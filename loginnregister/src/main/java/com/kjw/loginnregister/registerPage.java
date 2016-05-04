package com.kjw.loginnregister;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jiawei_kuang on 2015/12/3.
 * 注册页面
 */
public class registerPage extends AppCompatActivity{
     public static final int MSG_RECEIVED_CODE = 1;
    //private SmsObserver mObserver;
    private Button button1,button2,button3;
    private EditText sryzm=null;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what == MSG_RECEIVED_CODE){
                String code =(String)msg.obj;
                //刷新ui
                sryzm.setText(code);
            }
        }
    };
    
    @Override
    protected void onPause() {
        super.onPause();
        //getContentResolver().unregisterContentObserver(mObserver);
    }
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        
        button1=(Button)findViewById(R.id.tijiao);
        button2=(Button)findViewById(R.id.chongxie);
        button3=(Button)findViewById(R.id.yzm);


        //mObserver = new SmsObserver(registerPage.this, mHandler);
        Uri uri = Uri.parse("content://sms");//查询短信数据库
        //getContentResolver().registerContentObserver(uri, true, mObserver);

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //通过短信平台发短信给用户
            }
        });
    }
    
     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.jietu) {
                Toast.makeText(registerPage.this,"你想截图？",Toast.LENGTH_SHORT).show();
        }else if(id==R.id.fankui){
            Toast.makeText(registerPage.this,"你想反馈？",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(registerPage.this,"你想联系我们？",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
