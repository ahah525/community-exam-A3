package com.ll.exam;

import com.ll.exam.annotation.Autowired;
import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.Service;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.*;

public class Container {
    // 컴포넌트 저장을 위한 맵
    private static Map<Class, Object> objects;

    static {
        objects = new HashMap<>();
        // 컴포넌트 스캔
        scanComponents();
    }

    public static void scanComponents() {
        // 컴포넌트 스캔(Controller가 Service를 의존하므로 역순 생성)
        scanServices();
        scanControllers();
        // 의존관계 자동 주입
        resolveDependenciesAllComponents();
    }

    public static void scanServices() {
        Reflections ref = new Reflections("com.ll.exam");
        // @Service가 붙은 모든 클래스 객체 생성하여 등록
        for (Class<?> cls : ref.getTypesAnnotatedWith(Service.class)) {
            objects.put(cls, Ut.cls.newObj(cls, null));
        }
    }

    public static void scanControllers() {
        Reflections ref = new Reflections("com.ll.exam");
        // @Controller가 붙은 모든 클래스 객체 생성하여 등록
        for (Class<?> cls : ref.getTypesAnnotatedWith(Controller.class)) {
            objects.put(cls, Ut.cls.newObj(cls, null));
        }
    }

    // 모든 컴포넌트 의존관계 자동 주입
    public static void resolveDependenciesAllComponents() {
        for (Class cls : objects.keySet()) {
            Object o = objects.get(cls);
            // 해당 컴포넌트 의존관계 주입
            resolveDependencies(o);
        }
    }

    // 해당 컴포넌트 의존관계 자동 주입
    private static void resolveDependencies(Object o) {
        // 객체의 모든 필드 조회
        Arrays.asList(o.getClass().getDeclaredFields())
                .stream()
                .filter(f -> f.isAnnotationPresent(Autowired.class))    // @Autowired가 붙은 필드만
                .map(field -> {
                    // 모든 필드 접근 가능하도록 변경
                    field.setAccessible(true);
                    return field;
                })
                .forEach(field -> {
                    Class<?> cls = field.getType(); // 필드 타입
                    try {
                        // 필드에 값 주입(Map에 있는 해당 클래스 객체)
                        field.set(o, objects.get(cls));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
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
