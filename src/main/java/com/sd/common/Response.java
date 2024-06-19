package com.sd.common;


import lombok.Data;

@Data
public class Response<T> {

    // 状态码
    private int status;
    // 数据
    private T data;
    // 元数据
    private MetaData metaData;


    public Response(int status, MetaData metaData) {
        this.status = status;
        this.data = null;
        this.metaData = metaData;
    }

    public Response(int status, T data, MetaData metaData) {
        this.status = status;
        this.data = data;
        this.metaData = metaData;
    }
}
