package com.limai.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.limai.config.RoleConfig;
import com.limai.dao.UserMapper;
import com.limai.entity.User;
import com.limai.enumObj.ResMsgEnum;
import com.limai.myException.LoginException;
import com.limai.responseVo.BaseResponse;
import com.limai.responseVo.UserAuthenticationResponseVo;
import com.limai.service.EmployeeService;
import com.limai.service.RedisService;
import com.limai.service.UserAuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.LobRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName UserAuthenticationServiceImpl
 * @Description TODO
 * @Author LiuHaihua
 * @Date 2018/11/10 15:19
 * @Version 1.0
 **/
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService{

    private Logger logger = LoggerFactory.getLogger(UserAuthenticationServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private RoleConfig roleConfig;
    @Autowired
    private RedisService redisService;

    /**
     * @method login
     * @author LiuHaihua
     * @Description 用户登录接口
     * @Date 15:20 2018/11/10
     * @Param username 用户名
     * @Param password 密码
     * @return BaseResponse 用户登录结果
     **/
    @Override
    public BaseResponse login(@RequestParam String username, @RequestParam String password) {
        logger.info("login方法入参username:{},password:{}",username,password);
        BaseResponse baseResponse = new BaseResponse();
        try{
            User user = employeeService.queryEmployee(username,password);
            if(user != null && !StringUtils.isEmpty(user.getUserId())){
                //插入或者更新用户信息
                insertOrUpdateUser(user);
                String role = getUserRole(username);
                String token = UUID.randomUUID().toString().replace("-", "");
                //设置半小时的登陆超时时间
                redisService.setObject("bookmanage:authentication:"+token,user,Long.parseLong("1800"));
                UserAuthenticationResponseVo userAuthenticationResponseVo = new UserAuthenticationResponseVo();
                userAuthenticationResponseVo.setRole(role);
                userAuthenticationResponseVo.setToken(token);
                baseResponse.setStatus(ResMsgEnum.C00001.getStatus());
                baseResponse.setMessage(ResMsgEnum.C00001.getMessage());
                baseResponse.setData(userAuthenticationResponseVo);
            }else{
                baseResponse.setStatus(ResMsgEnum.L00003.getStatus());
                baseResponse.setMessage(ResMsgEnum.L00003.getMessage());
                baseResponse.setData(false);
            }
        }catch (Exception e){
            logger.error("login方法出参:{}"+e.getMessage());
            e.printStackTrace();
            throw new LoginException(ResMsgEnum.L00006);
        }
        logger.info("login方法出参:{}", JSONObject.toJSONString(baseResponse));
        return baseResponse;
    }

    @Override
    public BaseResponse logout(String token) {
        logger.info("logout方法,登出的token为{}",token);
        BaseResponse baseResponse = new BaseResponse();
        try{
            redisService.delete("bookmanage:authentication:"+token);
            baseResponse.setStatus(ResMsgEnum.C00001.getStatus());
            baseResponse.setMessage(ResMsgEnum.C00001.getMessage());
            baseResponse.setData(true);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("登出失败");
        }
        logger.info("logout方法,出参为:{}",JSONObject.toJSONString(baseResponse));
        return baseResponse;
    }

    /**
     * @method 判断用户是否存在
     * @author LiuHaihua
     * @Description 
     * @Date 23:31 2018/11/10
     * @Param user 用户实体
     * @return 用户在数据库中是否存在
     **/
    public void insertOrUpdateUser(User user){
        //查询数据库中是否有该用户
        String userId = user.getUserId();
        User userInDB = userMapper.selectUserByUserId(userId);
        if(userInDB != null){
            //存在用户，判断用户信息是否已经修改
            String mobile = userInDB.getMobile();
            String departmentId = userInDB.getDepartmentId();
            //只要这两者发生修改，就更新数据库
            if(!user.getMobile().equals(mobile) || !user.getDepartmentId().equals(departmentId)){
                //TODO
            }
        }else{
            //不存在用户，插入数据
            userMapper.insert(user);
        }
    }
    /**
     * @method getUserRole
     * @author LiuHaihua
     * @Description 
     * @Date 17:02 2018/11/10
     * @Param mobile 用户手机号
     * @return 用户角色
     **/
    public String getUserRole(String mobile){
        //默认普通用户
        String role = "3";
        List<String> adminList = roleConfig.getAdmin();
        List<String> headmanList = roleConfig.getHeadman();
        if(adminList != null && adminList.size()>0){
            if(adminList.contains(mobile)){
                role = "1";
            }else{
                if(headmanList.contains(mobile)){
                    role = "2";
                }
            }
        }
        logger.info("userRole方法出参:用户{}的角色为:{}",mobile,role);
        return role;
    }
}
