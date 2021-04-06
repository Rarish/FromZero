package com.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage<T> {
    private int code;
    private String message;
    private T data;

    public ResponseMessage(T data){
        this.data = data;
    }

    public static ResponseMessage success(){
        return new ResponseMessage(1,"success",null);
    }

    public static ResponseMessage success(String message){
        return new ResponseMessage(1,message,null);
    }

    public static ResponseMessage success(String message, Object data){
        return new ResponseMessage(1,message,data);
    }

    public static ResponseMessage success(Object data){
        return new ResponseMessage(1,"success",data);
    }

    public static ResponseMessage fail(){
        return new ResponseMessage(0,"fail",null);
    }

    public static ResponseMessage fail(String message){
        return new ResponseMessage(0,message,null);
    }

    public static ResponseMessage fail(String message, Object data){
        return new ResponseMessage(0,message,data);
    }
}
