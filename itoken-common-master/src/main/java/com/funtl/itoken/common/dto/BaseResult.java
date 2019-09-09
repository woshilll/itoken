package com.funtl.itoken.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 李洋
 * @date 2019-08-27 11:25
 */
@Data
public class BaseResult implements Serializable {
    private static final String RESULT_OK = "ok";
    private static final String RESULT_NOT_OK = "not_ok";
    private static final String SUCCESS = "操作成功";
    private String result;
    private Object data;
    private String success;
    private Cursor cursor;
    private List<Error> errors;

    public static BaseResult ok() {
        return createBaseResult(RESULT_OK , null , SUCCESS , null , null);
    }
    public static BaseResult ok(String message) {
        return createBaseResult(RESULT_OK , null , message , null , null);
    }
    public static BaseResult ok(Object data) {
        return createBaseResult(RESULT_OK , data , SUCCESS , null , null);
    }
    public static BaseResult ok(Object data, Cursor cursor) {
        return createBaseResult(RESULT_OK, data, SUCCESS, cursor, null);
    }
    public static BaseResult notOk() {
        return createBaseResult(RESULT_NOT_OK , null , "" , null , null);
    }
    public static BaseResult notOk(List<Error> errors) {
        return createBaseResult(RESULT_NOT_OK , null , "" , null , errors);
    }

    private static BaseResult createBaseResult(String result, Object data, String success, Cursor cursor, List<Error> errors) {
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setData(data);
        baseResult.setSuccess(success);
        baseResult.setCursor(cursor);
        baseResult.setErrors(errors);
        return baseResult;
    }

    @Data
    public static class Cursor {
        private int total;
        private int offset;
        private int limit;
    }

    @Data
    @AllArgsConstructor
    public static class Error {
        private String field;
        private String message;
    }
}
