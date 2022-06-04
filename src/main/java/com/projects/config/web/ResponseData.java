package com.projects.config.web;

import lombok.Data;


@Data
public class ResponseData {

    public static final String DEFAULT_SUCCESS_MESSAGE = "璇锋眰鎴愬姛";

    public static final String DEFAULT_ERROR_MESSAGE = "缃戠粶寮傚父";

    public static final Integer DEFAULT_SUCCESS_CODE = 200;

    public static final Integer DEFAULT_ERROR_CODE = 500;

    /**
     * 璇锋眰鏄惁鎴愬姛
     */
    private Boolean success;

    /**
     * 鍝嶅簲鐘舵�佺爜
     */
    private Integer code;

    /**
     * 鍝嶅簲淇℃伅
     */
    private String message;

    /**
     * 鍝嶅簲瀵硅薄
     */
    private Object data;

    public ResponseData() {
    }

    public ResponseData(Boolean success, Integer code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static SuccessResponseData success() {
        return new SuccessResponseData();
    }

    public static SuccessResponseData success(Object object) {
        return new SuccessResponseData(object);
    }

    public static SuccessResponseData success(Integer code, String message, Object object) {
        return new SuccessResponseData(code, message, object);
    }

    public static ErrorResponseData error(String message) {
        return new ErrorResponseData(message);
    }

    public static ErrorResponseData error(Integer code, String message) {
        return new ErrorResponseData(code, message);
    }

    public static ErrorResponseData error(Integer code, String message, Object object) {
        return new ErrorResponseData(code, message, object);
    }
}
