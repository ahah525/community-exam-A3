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

    // /usr/article/list GET 요청 처리
    @GetMapping("/usr/article/list")
    public void showList() {

    }

    // 테스트 용도
    public ArticleService getArticleServiceForTest() {
        return articleService;
    }
}
