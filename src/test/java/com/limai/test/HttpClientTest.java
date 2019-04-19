package com.limai.test;


import com.alibaba.fastjson.JSON;
import com.limai.entity.Person;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class HttpClientTest {


    public static void main(String[] args){
        //简单使用
        simpleUse();
//        spellParam();
//        useURI();
//        postNoParam();
//        postCommonParam();
//        postObjectParam();
//        postObjectAndCommonParam();


    }

    //连接池
    public static void httpClientConnPool(){
        PoolingHttpClientConnectionManager poolManager = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(null).build();

    }

    //发送post请求，参数是对象和普通参数混合
    public static void postObjectAndCommonParam(){
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            //使用URId
            URI uri = new URIBuilder().setScheme("http")
                    .setHost("localhost").setPort(8081)
                    .setPath("/bookmanage/httpClient/postObjectAndCommonParam")
                    .setParameter("method","post")
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            Person person = new Person("zhangsan","女",33);
            StringEntity stringEntity = new StringEntity(JSON.toJSONString(person),"UTF-8");
            //设置请求体
            httpPost.setEntity(stringEntity);
            //设置header
            httpPost.setHeader("Content-Type","application/json;charset=utf8");
            //发送请求
            response = httpClient.execute(httpPost);
            System.out.println("请求响应状态"+response.getStatusLine());
            HttpEntity httpEntity = response.getEntity();
            if(httpEntity != null){
                System.out.println("响应的内容长度："+httpEntity.getContentLength());
                System.out.println("响应的内容："+EntityUtils.toString(httpEntity));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //关闭连接
                if(httpClient != null){
                    httpClient.close();
                }
                if(response != null){
                    response.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    //post请求，发送请求，简单对象
    public static void  postObjectParam(){
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost("http://localhost:8081/bookmanage/httpClient/postObjectParam");
            Person person = new Person("刘海华","男",27);
            StringEntity stringEntity = new StringEntity(JSON.toJSONString(person),"utf-8");
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Content-Type","application/json;charset=utf8");
            //发送http post请求
            response = httpClient.execute(httpPost);
            System.out.println("请求响应状态"+response.getStatusLine());
            HttpEntity httpEntity = response.getEntity();
            if(httpEntity != null){
                System.out.println("响应的内容长度："+httpEntity.getContentLength());
                System.out.println("响应的内容："+EntityUtils.toString(httpEntity));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //关闭连接
                if(httpClient != null){
                    httpClient.close();
                }
                if(response != null){
                    response.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //post请求，普通参数
    public static void postCommonParam(){
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringBuffer param = new StringBuffer();
        try {
            //字符数据最好encoding以下;某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
            param.append("name="+ URLEncoder.encode("zhangsan&","utf-8"));
            param.append("&age=27");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            //设置post请求的路径
            HttpPost httpPost = new HttpPost("http://localhost:8081/bookmanage/httpClient/postCommonParam?"+param);
            // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
            httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");
            httpPost.setHeader("Content-Type","multipart/form-data;charset=utf8");
            httpPost.setHeader("Content-Type","application/json;charset=utf8");
            httpPost.setHeader("Content-Type","application/xml;charset=utf8");
            httpPost.setHeader("Content-Type","text/xml;charset=utf8");
            //执行post请求
            response = httpClient.execute(httpPost);
            System.out.println("请求响应状态"+response.getStatusLine());
            HttpEntity httpEntity = response.getEntity();
            if(httpEntity != null){
                System.out.println("响应的内容长度："+httpEntity.getContentLength());
                System.out.println("响应的内容："+EntityUtils.toString(httpEntity));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //关闭连接
                if(httpClient != null){
                    httpClient.close();
                }
                if(response != null){
                    response.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

  //post请求无参数
    public static void postNoParam(){
        CloseableHttpClient httpClient = HttpClients.custom().build();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost("http://localhost:8081/bookmanage/httpClient/postNoParam");
            //客户端执行发送请求操作
            response = httpClient.execute(httpPost);
            System.out.println("请求响应状态："+response.getStatusLine());
            //从响应模型中获取响应实体
            HttpEntity httpEntity = response.getEntity();
            if( httpEntity != null){
                System.out.println("响应结果长度："+httpEntity.getContentLength());
                System.out.println("响应结果："+EntityUtils.toString(httpEntity));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //关闭连接
                if(httpClient != null){
                    httpClient.close();
                }
                if(response != null){
                    response.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //使用URI方式
    public static void useURI(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri = null;
        CloseableHttpResponse response = null;
        try {
             uri = new URIBuilder().setScheme("http")
                        .setHost("47.94.221.48")
                        .setPort(9200)
                        .setPath("/index/user/dEi4-2kB1eKGSGU7dNSr")
                        .setParameter("_source","name,age,sex")
                        .build();
            HttpGet httpGet = new HttpGet(uri);
            response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            System.out.println("请求响应状态"+response.getStatusLine());
            System.out.println("请求结果"+ EntityUtils.toString(httpEntity));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(httpClient != null){
                    httpClient.close();
                }
                if(response != null){
                    response.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //get的方式拼接参数
    public static void  spellParam(){
         CloseableHttpClient httpClient = HttpClientBuilder.create().build();
         StringBuffer stringBuffer = new StringBuffer();
         stringBuffer.append("_source=name,age");
         HttpGet httpGet = new HttpGet("http://47.94.221.48:9200/index/user/dEi4-2kB1eKGSGU7dNSr?"+stringBuffer.toString());

        // 配置信息
        RequestConfig requestConfig = RequestConfig.custom()
                // 设置连接超时时间(单位毫秒)
                .setConnectTimeout(5000)
                // 设置请求超时时间(单位毫秒)
                .setConnectionRequestTimeout(5000)
                // socket读写超时时间(单位毫秒)
                .setSocketTimeout(5000)
                // 设置是否允许重定向(默认为true)
                .setRedirectsEnabled(true).build();
         httpGet.setConfig(requestConfig);

         CloseableHttpResponse response = null;
        try {
           response = httpClient.execute(httpGet);
           System.out.println("响应状态为"+response.getStatusLine());
           HttpEntity httpEntity = response.getEntity();
           System.out.println(EntityUtils.toString(httpEntity));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                try {
                    if(httpClient != null){
                        httpClient.close();
                    }
                    if(response != null){
                        response.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    //简单的使用
    public static void simpleUse(){
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://47.94.221.48:9200/website/blog/8Dsz3WkB1eKGSGU7PtU7");
        CloseableHttpResponse closeableHttpResponse = null;
        try {
            //设置服务器端不压缩数据，返回给客户端
            httpGet.setHeader(  "Accept-Encoding", "identity");
            closeableHttpResponse = closeableHttpClient.execute(httpGet);
            HttpEntity httpEntity = closeableHttpResponse.getEntity();
            if( httpEntity != null){
                System.out.println("输出内容的长度为："+httpEntity.getContentLength());
                System.out.println(EntityUtils.toString(httpEntity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(closeableHttpClient != null){
                    closeableHttpClient.close();
                }
                if(closeableHttpResponse != null){
                    closeableHttpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
