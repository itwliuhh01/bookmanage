package com.limai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName RoleConfig
 * @Description 系统管理员配置类
 * @Author LiuHaihua
 * @Date 2018/11/10 16:53
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "bookmanage.role")
public class RoleConfig {
    private List<String> admin;
    private List<String> headman;

    public List<String> getAdmin() {
        return admin;
    }

    public void setAdmin(List<String> admin) {
        this.admin = admin;
    }

    public List<String> getHeadman() {
        return headman;
    }

    public void setHeadman(List<String> headman) {
        this.headman = headman;
    }
}
