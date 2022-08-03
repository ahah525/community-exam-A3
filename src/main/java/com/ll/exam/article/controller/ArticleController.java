package com.ll.exam.article.controller;

import com.ll.exam.Rq;
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
    public void showList(Rq rq) {
        rq.println("게시물 리스트");
    }

    // /usr/article/detail/{boardCode} GET 요청 처리
    @GetMapping("/usr/article/detail/{boardCode}/{id}")
    public void showDetail(Rq rq) {
        rq.println("게시물 상세페이지<br>");

        // long id = rq.getLongPathValueByIndex(1, -1);
        String boardCode = rq.getParam("boardCode", ""); // 곧 기능 구현
        long id = rq.getLongParam("id", -1); // 곧 기능 구현

        rq.println("%s 게시판, %d번 게시물".formatted(boardCode, id));
    }

    //
    @GetMapping("/usr/article/modify/{boardCode}/{id}")
    public void showModify(Rq rq) {
        rq.println("게시물 수정페이지<br>");

        // long id = rq.getLongPathValueByIndex(1, -1);
        String boardCode = rq.getParam("boardCode", ""); // 곧 기능 구현
        long id = rq.getLongParam("id", -1); // 곧 기능 구현

        rq.println("%s 게시판, %d번 게시물".formatted(boardCode, id));
    }
}
