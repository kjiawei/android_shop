package com.kjw.loginnregister;

import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by jiawei_kuang on 2015/12/3
 * 向服务器发送数据
 */
public class NewService {
    public static boolean save(String name,String phone){
        String path = "http://<span style=\"color: #ff0000;\"><strong>192.168.1.1</strong></span>:8080/Register/ManageServlet";
        Map<String,String> custom = new HashMap<String,String>();
        custom.put("name",name);
        custom.put("phone",phone);
        try{
            return SendGETRequest(path,custom,"UTF-8");
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }


    //GET请求
    private static boolean SendGETRequest(String path,Map<String,String> custom,String ecoding)throws Exception{
        //e.g.http://192.168.123.1:8080/Register/ManagerServlet?name=123&phone=18826405374
        StringBuilder url = new StringBuilder(path);
        url.append("?");
        for(Map.Entry<String,String>map:custom.entrySet()){
            url.append(map.getKey()).append("=");
            url.append(URLEncoder.encode(map.getValue(), ecoding));
            url.append("&");
        }
        url.deleteCharAt(url.length()-1);
        System.out.println(url);
        HttpsURLConnection conn = (HttpsURLConnection)new URL(url.toString()).openConnection();
        conn.setConnectTimeout(100000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == 200){
            return true;
        }
        return false;
    }
}
