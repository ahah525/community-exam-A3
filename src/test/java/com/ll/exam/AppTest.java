package com.ll.exam;

import com.ll.exam.article.controller.ArticleController;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    public void junit_assertThat() {
        int rs = 10 + 20;

        assertThat(rs).isEqualTo(30);
    }

    @Test
    public void ioc__articleController() {
        // when: con에서 ArticleController 받아옴
        ArticleController articleController = Con.getArticleController();
        // then: 받아온 articleController가 null이 아닌지 검증
        assertThat(articleController).isNotNull();
    }

    @Test
    public void ioc__articleController__2() {
        ArticleController articleController1 = Con.getArticleController();
        ArticleController articleController2 = Con.getArticleController();
        // then: 같은 객체가 반환되는지 검증(싱글톤 검증)
        assertThat(articleController1).isEqualTo(articleController2);
    }
}
