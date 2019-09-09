package com.funtl.itoken.common.constants;

/**
 * @author 李洋
 * @date 2019-08-28 10:36
 */
public enum  StatusCodesConstant {
    /**
     *
     */
    BAD_GATEWAY(502, "从上游服务器接收到无效响应");
    private int code;
    private String message;
    private StatusCodesConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
