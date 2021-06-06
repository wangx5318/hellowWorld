package com.example.demo.enum2;

/**
 *  Http请求状态码
 *
 * */
public enum HttpStatusEnum {

    OK(200,"OK"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "您还未认证，请先进行认证！"),
    FORBIDDEN(403, "您没此权限，请联系管理员"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");


    private Integer code;

    private String message;

    HttpStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
