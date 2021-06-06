package com.example.demo.entity;

import com.example.demo.enum2.HttpStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

import java.io.Serializable;


/**
 * @Description: TODO
 * @author: scott
 * @date: 2021-05-27 9:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonComponent
public class ResultVo <T>  {

    private Integer code;

    private String message;

    private T data;

    public static<T> ResultVo success(){
        return new ResultVo(HttpStatusEnum.OK.getCode(),HttpStatusEnum.OK.getMessage(),null);
    }

    public static<T> ResultVo success(T data){
        return new ResultVo(HttpStatusEnum.OK.getCode(),HttpStatusEnum.OK.getMessage(),data);
    }
    public static<T> ResultVo success(String message,T data){
        return new ResultVo(HttpStatusEnum.OK.getCode() , message , data);
    }
    public static<T> ResultVo fail(){
        return new ResultVo(HttpStatusEnum.INTERNAL_SERVER_ERROR.getCode() , HttpStatusEnum.INTERNAL_SERVER_ERROR.getMessage() , null);
    }

    public static<T> ResultVo fail(Integer code,String message){
        return new ResultVo(code , message , null);
    }
}
