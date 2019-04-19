package com.limai.service;

import com.limai.entity.User;
import com.limai.responseVo.BaseResponse;

public interface EmployeeService {
    public User queryEmployee(String nusername, String password);
    public BaseResponse queryAlLEmployee(String corpId, String Authorization);
    public BaseResponse queryALLDepts(String corpId, String Authorization);
}
