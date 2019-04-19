package com.limai.test;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

//restTemplate测试类
public class RestTemplateTest {

    public static void main(String[] args){

        RestTemplate restTemplate = new RestTemplate();
        //底层调用java.net包发送请求
//        SimpleClientHttpRequestFactory scrf = new SimpleClientHttpRequestFactory();
//        restTemplate.setRequestFactory(scrf);

        //底层使用httpclient
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(httpComponentsClientHttpRequestFactory);

        JSONObject result = restTemplate.getForObject("http://47.94.221.48:9200/index/user/dEi4-2kB1eKGSGU7dNSr", JSONObject.class);
        System.out.println(result.toJSONString());

    }

}
