package com.teclan.lottery.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class HttpUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient CLIENT = new OkHttpClient();

    private static String[] headers;

    public static void setHeaders(String[] nameAndValue){
        headers=nameAndValue ;
    }

    private static String[] getHeaders(){
        return headers;
    }

    public static void setHeaders(Map<String,String> nameAndValue){
        headers=new String[nameAndValue.size()*2] ;
        int index=0;
        for(String k:nameAndValue.keySet()){
            headers[index++]=k;
            headers[index++]=nameAndValue.get(k);
        }
    }

    public static String getString(String url,boolean isGzip) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .headers(Headers.of(getHeaders()))
                .build();
        LOGGER.info("请求：{}",url);
        String ret = "";
        Response response = CLIENT.newCall(request).execute();
        if(isGzip) {
            InputStream is = new GZIPInputStream(new BufferedInputStream(response.body().byteStream()));
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[4096];
            for (int n; (n = is.read(b)) != -1; ) {
                out.append(new String(b, 0, n));
            }
            ret = out.toString();
        }else {
            ret = response.body().string();
        }
        return ret;
    }

    public static JSONObject get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .headers(Headers.of(getHeaders()))
                .build();
        LOGGER.info("请求：{}",url);
        Response response = CLIENT.newCall(request).execute();
        String ret = response.body().string();
        return JSONObject.parseObject(ret, Feature.OrderedField);
    }

    public static JSONObject post(String url, JSONObject paramater) throws Exception {
        RequestBody body = RequestBody.create(JSON, paramater.toJSONString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(Headers.of(getHeaders()))
                .build();
        try {
            LOGGER.info("请求：{}",url);
            Response response = CLIENT.newCall(request).execute();
            String ret = response.body().string();
            return JSONObject.parseObject(ret, Feature.OrderedField);
        }catch (Exception e){
            if(e.getMessage().contains("Failed to connect to")){
                throw new Exception(String.format("无法连接 %s，请确认服务已启动且网络可连通 ...",url));
            }else{
                throw e;
            }
        }
    }

    public static JSONObject post(String url,JSONObject paramater, String... namesAndValues ) throws IOException {
        RequestBody body = RequestBody.create(JSON, paramater.toJSONString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(Headers.of(namesAndValues))
                .build();
        LOGGER.info("请求：{}",url);
        Response response = CLIENT.newCall(request).execute();
        String ret = response.body().string();
        return JSONObject.parseObject(ret, Feature.OrderedField);
    }
}
