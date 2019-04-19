package com.limai.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.limai.config.DingDingUrlConfig;
import com.limai.entity.User;
import com.limai.enumObj.ResMsgEnum;
import com.limai.responseVo.BaseResponse;
import com.limai.service.EmployeeService;
import com.limai.service.RedisService;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;
    @Autowired
    private DingDingUrlConfig dingDingUrlConfig;
    @Autowired
    public EmployeeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public User queryEmployee(String username,String password) {
        // 要调用的接口方法
        String url = dingDingUrlConfig.getEmployeeUrl();
        User user = new User();
        try{
            JSONObject jsonObject = null;
            Map<String,Object> requestInfo= new HashMap<>();
            requestInfo.put("mobile",username);
            requestInfo.put("password",password);
            String requestStr=JSON.toJSONString(requestInfo);
            JSONObject json=JSON.parseObject(requestStr);
            String result = restTemplate.postForEntity(url, json, String.class).getBody();
            JSONObject resultObject = JSONObject.parseObject(result);
           //查询钉钉成功
           if(null!= resultObject.getString("userid")){
               user = JSONObject.toJavaObject(resultObject,User.class);
               user.setId(UUID.randomUUID().toString().replaceAll("-",""));
               user.setUserId(resultObject.getString("userid"));
               user.setPassword(password);
               user.setUsername(resultObject.getString("name"));
               user.setDepartmentId(resultObject.getString("department"));
               user.setJobNumber(resultObject.getString("jobnumber"));
               user.setDingToken(resultObject.getString("token"));
               int active = resultObject.getBoolean("active")?1:0;
               user.setActive(active);
           }
            logger.info("dingding result:"+result+"---"+user.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
           
        }
        return user;

    }
    /**
     * @method queryAlLEmployee
     * @author LiuHaihua
     * @Description 查询藜麦所有的员工信息
     * @Date 17:21 2018/11/9
     * @Param corpId 企业id
     * @Param Authorization 授权令牌 （ 请放置在header中 )
     * @return JSONObject 所有员工信息
     **/
    @Override
    public BaseResponse queryAlLEmployee(String corpId, String Authorization) {
        BaseResponse baseResponse = new BaseResponse();
        logger.info("queryAlLEmployee方法入参corpId:{},Authorization:{}",corpId,Authorization);
        try{
            //从redis中获取存储的组织结构图
            String[] result = (String[]) redisService.getObject("bookmanage:allEmp");
            JSONArray jsonAray = new JSONArray();
            if(StringUtils.isEmpty(result)){
                logger.info("将所有员工信息设置到redis,下次查询从redis中查询");
                String url = dingDingUrlConfig.getAllEmployeesUrl()+"?corpId="+corpId;
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.add("Authorization",Authorization);
                HttpEntity<String> requestEntity = new HttpEntity<String>(null,requestHeaders);
                String employeeStr = restTemplate.exchange(url, HttpMethod.GET,requestEntity,String.class).getBody();
                //切割分成单个员工信息
                result = employeeStr.split("\n");
                //设置过期时间
                redisService.setObject("bookmanage:allEmp",result,Long.parseLong("300"));
            }
            for(String str : result){
                jsonAray.add(JSONObject.parse(str));
            }
            baseResponse.setMessage(ResMsgEnum.C00001.getMessage());
            baseResponse.setStatus(ResMsgEnum.C00001.getStatus());
            baseResponse.setData(jsonAray);
        }catch (Exception e){
            e.printStackTrace();;
            baseResponse.setData(false);
            baseResponse.setMessage(ResMsgEnum.C00002.getMessage());
            baseResponse.setStatus(ResMsgEnum.C00002.getStatus());
        }
        logger.info("queryAlLEmployee方法出参",JSONObject.toJSONString(baseResponse));
        return baseResponse;
    }
    /**
     * @method 查询部门下面的员工
     * @author LiuHaihua
     * @Description 
     * @Date 18:11 2018/11/12
     * @Param deptNo部门编号
     * @return 部门所有的员工
     **/
    public JSONArray getDeptEmployee(String deptNo,String corpId, String Authorization){
        BaseResponse result = queryAlLEmployee(corpId,Authorization);
        JSONArray allEmployees = new JSONArray();
        if("1".equals(result.getStatus())){
            JSONArray array = (JSONArray)result.getData();
            for(int i =0;i<array.size();i++){
                JSONArray deptArray = ((JSONObject)array.get(i)).getJSONArray("department");
                if (deptArray.contains(Integer.parseInt(deptNo))){
                    allEmployees.add(array.get(i));
                }
            }
        }else {
            throw new RuntimeException("获取部门信息异常");
        }
        return allEmployees;
    }
    
    /**
     * @method queryALLDepts
     * @author LiuHaihua
     * @Description 查询藜麦所有的部门
     * @Date 17:22 2018/11/9
     * @Param corpId 企业id
     * @Param Authorization 授权令牌 （ 请放置在header中 )
     * @return 所有部门
     **/
    @Override
    public BaseResponse queryALLDepts(String corpId, String Authorization) {
        BaseResponse baseResponse = new BaseResponse();
        logger.info("queryALLDepts方法入参corpId:{},Authorization:{}",corpId,Authorization);
        try{
            String url = dingDingUrlConfig.getAllDeptsUrl()+"?corpId="+corpId;
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Authorization",Authorization);
            HttpEntity<String> requestEntity = new HttpEntity<String>(null,requestHeaders);
            String result = restTemplate.exchange(url, HttpMethod.GET,requestEntity,String.class).getBody();
            //切割分成单个部门的信息
            String[] deptStr = result.split("\n");
            JSONArray jsonArray = new JSONArray();
            for(String str : deptStr){
                JSONObject object = (JSONObject) JSONObject.parse(str);
                String departmentId = object.getString("id");
                JSONArray employyes = getDeptEmployee(departmentId,corpId,Authorization);
                object.put("employyes",employyes);
                jsonArray.add(object);
            }
            baseResponse.setMessage(ResMsgEnum.C00001.getMessage());
            baseResponse.setStatus(ResMsgEnum.C00001.getStatus());
            baseResponse.setData(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
            baseResponse.setData(false);
            baseResponse.setMessage(ResMsgEnum.C00002.getMessage());
            baseResponse.setStatus(ResMsgEnum.C00002.getStatus());
            throw new RuntimeException("查询组织部门失败");
        }
        logger.info("queryALLDepts方法出参:{}",JSONObject.toJSONString(baseResponse));
        return baseResponse;
    }
}
