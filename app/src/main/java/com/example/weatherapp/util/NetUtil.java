package com.example.weatherapp.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUtil {
    public static final String RUL_WEATHER_WITH_FUTURE = "https://tianqiapi.com/api?version=v1&appid=63796812&appsecret=tUUvoOg5";

    public static String doGet(String urlStr){
        String result = "";
        InputStreamReader inputStreamReader= null;
        BufferedReader bufferedReader = null;
        //连接网络
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            //从连接中读取数据（二进制）
            InputStream inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            //二进制流送入缓冲区
            bufferedReader = new BufferedReader(inputStreamReader);
            //从缓冲区中一行行读取字符串
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine())!= null){
                stringBuilder.append(line);
            }
            result = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                connection.disconnect();
            }
            if (inputStreamReader != null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return result;
    }

    public static String getWeatherOfCity(String city){

        //拼接出获取天气数据的URL
        String weatherUrl = RUL_WEATHER_WITH_FUTURE+"&city="+city;
        Log.d("fan", "---------weatherUrl-----------"+weatherUrl);
        String weatherResult = doGet(weatherUrl);
        Log.d("fan", "---------weatherResult-----------"+weatherResult);
        return weatherResult;

    }
}
