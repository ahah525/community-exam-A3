package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.article.controller.ArticleController;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

public class Con {
    private static final ArticleController articleController;

    static {
        articleController = new ArticleController();
    }

    public static ArticleController getArticleController() {
         return articleController;
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
