package com.limai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @ClassName DingDingUrlConfig
 * @Description 配置调用钉钉的链接
 * @Author LiuHaihua
 * @Date 2018/11/13 8:09
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "limai.dingding")
public class DingDingUrlConfig {
    private String employeeUrl;
    private String allEmployeesUrl;
    private String allDeptsUrl;

    public String getAllDeptsUrl() {
        return allDeptsUrl;
    }

    public void setAllDeptsUrl(String allDeptsUrl) {
        this.allDeptsUrl = allDeptsUrl;
    }

    public String getAllEmployeesUrl() {
        return allEmployeesUrl;
    }

    public void setAllEmployeesUrl(String allEmployeesUrl) {
        this.allEmployeesUrl = allEmployeesUrl;
    }

    public String getEmployeeUrl() {
        return employeeUrl;
    }

    public void setEmployeeUrl(String employeeUrl) {
        this.employeeUrl = employeeUrl;
    }
}
