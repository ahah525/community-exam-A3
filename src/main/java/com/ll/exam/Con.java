package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.home.controller.HomeController;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Con {
    // 컴포넌트 저장을 위한 맵
    private static Map<Class, Object> objects;

    static {
        objects = new HashMap<>();
        // 최초 1번 실행
        objects.put(ArticleController.class, new ArticleController());
        objects.put(HomeController.class, new HomeController());
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
