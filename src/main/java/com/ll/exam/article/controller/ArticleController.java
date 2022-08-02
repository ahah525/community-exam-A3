package com.ll.exam.article.controller;

import com.ll.exam.annotation.Autowired;
import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;
import com.ll.exam.article.service.ArticleService;

// ArticleController == controller
@Controller
public class ArticleController {
    // ArticleController는 ArticleService에 의존한다
    @Autowired
    private ArticleService articleService;

    // /usr/article/list/{} GET 요청 처리
    @GetMapping("/usr/article/list/{boardCode}")
    public void showList() {

    }

    // /usr/article/detail/{boardCode} GET 요청 처리
    @GetMapping("/usr/article/detail/{boardCode}")
    public void showDetail() {

    }
}
