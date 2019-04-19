package com.limai.responseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "基础请求响应实体")
public class BaseResponse {
    @ApiModelProperty(notes = "响应code",example = "1")
    private String status;
    @ApiModelProperty(notes = "响应状态说明",example = "请求成功")
    private String message;
    @ApiModelProperty(notes = "返回数据",example = "返回数据")
    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
