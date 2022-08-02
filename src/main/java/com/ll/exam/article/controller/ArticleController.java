package com.ll.exam.article.controller;

import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;

// ArticleController == controller
@Controller
public class ArticleController {

    // /usr/article/list GET 요청 처리
    @GetMapping("/usr/article/list")
    public void showList() {

    }
}
