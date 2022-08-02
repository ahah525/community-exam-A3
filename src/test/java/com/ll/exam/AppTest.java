package com.ll.exam;

import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.home.controller.HomeController;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        ArticleController articleController = Con.getObj(ArticleController.class);
        // then: 받아온 articleController가 null이 아닌지 검증
        assertThat(articleController).isNotNull();
    }

    @Test
    public void ioc__articleController__싱글톤() {
        ArticleController articleController1 = Con.getObj(ArticleController.class);
        ArticleController articleController2 = Con.getObj(ArticleController.class);
        // then: 같은 객체가 반환되는지 검증(싱글톤 검증)
        assertThat(articleController1).isEqualTo(articleController2);
    }

    @Test
    public void ioc__homeController() {
        HomeController homeController = Con.getObj(HomeController.class);

        assertThat(homeController).isNotNull();
    }

    @Test
    public void ioc__homeController__싱글톤() {
        HomeController homeController1 = Con.getObj(HomeController.class);
        HomeController homeController2 = Con.getObj(HomeController.class);

        assertThat(homeController2).isEqualTo(homeController1);
    }

    @Test
    public void ioc__Controller들을_스캔하여_수집() {
        // when: 컴포넌트 스캔
        List<String> names = Con.getControllerNames();
        // then: @Controller가 붙은 컴포넌트 모두 조회되었는지 검증
        assertThat(names).contains("Home");
        assertThat(names).contains("Article");
    }
}
