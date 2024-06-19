package com.sd.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class TabBar {
    private int id;
    // 导航内容
    private String authName;
    // 导航路径
    private String path;
}
