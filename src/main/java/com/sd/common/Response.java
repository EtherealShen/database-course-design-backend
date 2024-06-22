package com.sd.common;


import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    // 状态码
    private int code;
    // 数据
    private T data;
    // 信息
    private String msg;

    public static Response success(){
        return new Response(200,null,"操作成功");
    }
    public static Response success(Object data){
        return new Response(200,data,"操作成功");
    }
    public static Response success(Object data,String msg){
        return new Response(200,data,msg);
    }
    public static Response error(String msg){
        return new Response(400,null,msg);
    }
    public static Response error(int code,String msg){
        return new Response(code,null,msg);

    }
    public static Response error(){
        return new Response(400,null,"操作失败");

    }
}
