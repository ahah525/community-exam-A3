package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.Service;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container {
    // 컴포넌트 저장을 위한 맵
    private static Map<Class, Object> objects;

    static {
        objects = new HashMap<>();
        // 컴포넌트 스캔
        scanComponents();
    }

    public static void scanComponents() {
        //
        scanServices();
        scanControllers();
    }

    public static void scanServices() {
        Reflections ref = new Reflections("com.ll.exam");
        // @Service가 붙은 모든 객체 생성하여 등록
        for (Class<?> cls : ref.getTypesAnnotatedWith(Service.class)) {
            objects.put(cls, Ut.cls.newObj(cls, null));
        }
    }

    public static void scanControllers() {
        Reflections ref = new Reflections("com.ll.exam");
        // @Controller가 붙은 모든 객체 생성하여 등록
        for (Class<?> cls : ref.getTypesAnnotatedWith(Controller.class)) {
            objects.put(cls, Ut.cls.newObj(cls, null));
        }
    }

    // 객체 반환
    public static <T> T getObj(Class cls) {
        return (T) objects.get(cls);
    }

    // @Controller가 붙은 컴포넌트 스캔
    public static List<String> getControllerNames() {
        List<String> names = new ArrayList<>();
        // 스캔 범위 지정(com.ll.exam 하위)
        Reflections ref = new Reflections("com.ll.exam");
        for (Class<?> cls : ref.getTypesAnnotatedWith(Controller.class)) {
            names.add(cls.getSimpleName().replace("Controller", ""));
        }
        return names;
    }
}
