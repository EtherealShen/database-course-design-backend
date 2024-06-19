package com.sd.common;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MetaData {
    private int status;
    private String msg;
    private String token="";

    public MetaData(int status,String msg){
        this.status = status;
        this.msg = msg;
    }
}