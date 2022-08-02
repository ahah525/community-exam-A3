package com.ll.exam;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;

@AllArgsConstructor
public class RouteInfo {
    private String path;    // 요청 path
    private String actionPath;      // path(파라미터 제외)
    @Getter
    private Class controllerCls;    // 컨트롤러 클래스
    @Getter
    private Method method;  // http 요청 메서드
}