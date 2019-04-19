package com.limai.responseVo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName UserAuthenticationResponseVo
 * @Description 返回用户登录的信息
 * @Author LiuHaihua
 * @Date 2018/11/12 9:53
 * @Version 1.0
 **/
public class UserAuthenticationResponseVo {
    //随机生成的uuid
    @ApiModelProperty(value = "",notes = "生成的令牌")
    private String token;
    @ApiModelProperty(value = "",notes = "用户角色1:管理员2:组长3:普通用户")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
