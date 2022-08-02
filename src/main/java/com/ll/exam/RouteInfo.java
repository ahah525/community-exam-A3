package com.ll.exam;

import lombok.AllArgsConstructor;

import java.lang.reflect.Method;

@AllArgsConstructor
public class RouteInfo {
    private String path;    // 요청 path
    private Method method;  // http 요청 메서드
}