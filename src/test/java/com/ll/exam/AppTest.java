package com.ll.exam;

import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.article.repository.ArticleRepository;
import com.ll.exam.home.controller.HomeController;
import com.ll.exam.article.service.ArticleService;
import com.ll.exam.util.Ut;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    public void junit_assertThat() {
        int rs = 10 + 20;

        assertThat(rs).isEqualTo(30);
    }

    @Test
    public void ioc__articleController() {
        // when: 컨테이너에서 ArticleController 받아오기
        ArticleController articleController = Container.getObj(ArticleController.class);
        // then: 받아온 객체가 null이 아닌지 검증
        assertThat(articleController).isNotNull();
    }

    @Test
    public void ioc__articleController__싱글톤() {
        ArticleController articleController1 = Container.getObj(ArticleController.class);
        ArticleController articleController2 = Container.getObj(ArticleController.class);
        // then: 같은 객체가 반환되는지 검증(싱글톤 검증)
        assertThat(articleController1).isEqualTo(articleController2);
    }

    @Test
    public void ioc__homeController() {
        HomeController homeController = Container.getObj(HomeController.class);
        // then: 받아온 객체가 null이 아닌지 검증
        assertThat(homeController).isNotNull();
    }

    @Test
    public void ioc__homeController__싱글톤() {
        HomeController homeController1 = Container.getObj(HomeController.class);
        HomeController homeController2 = Container.getObj(HomeController.class);

        assertThat(homeController2).isEqualTo(homeController1);
    }

    @Test
    public void ioc__Controller들을_스캔하여_수집() {
        // when: 컴포넌트 스캔
        List<String> names = Container.getControllerNames();
        // then: @Controller가 붙은 컴포넌트 모두 조회되었는지 검증
        assertThat(names).contains("Home");
        assertThat(names).contains("Article");
    }

    @Test
    public void ioc__articleService() {
        // when: 컨테이너에서 ArticleService 객체 받아오기
        ArticleService articleService = Container.getObj(ArticleService.class);
        // then: 받아온 객체가 null이 아닌지 검증
        assertThat(articleService).isNotNull();
    }

    @Test
    public void ioc__articleService__싱글톤() {
        // when: 컨테이너에서 ArticleService 객체 여러번 받아오기
        ArticleService articleService1 = Container.getObj(ArticleService.class);
        ArticleService articleService2 = Container.getObj(ArticleService.class);
        // then: 받아온 객체가 같은지 검증(싱글톤 검증)
        assertThat(articleService2).isEqualTo(articleService1);
    }

    @Test
    public void articleController를_생성할때_articleService도_같이_생성() {
        ArticleController articleController = Container.getObj(ArticleController.class);
        // when: articleController에 articleService 필드명을 가진 필드값 조회
        ArticleService articleService = Ut.reflection.getFieldValue(articleController, "articleService", null);
        // then:
        assertThat(articleService).isNotNull();
    }

    @Test
    public void articleService를_생성할때_articleRepository도_같이_생성() {
        ArticleService articleService = Container.getObj(ArticleService.class);
        // when:
        ArticleRepository articleRepository = Ut.reflection.getFieldValue(articleService, "articleRepository", null);
        // then:
        assertThat(articleRepository).isNotNull();
    }

    @Test
    public void ControllerManager__scanMappings() {
        ControllerManager.init(); // 클래스를 강제로 로딩되게 하려는 목적
    }

    @Test
    public void ControllerManager__라우트정보_개수() {
        Map<String, RouteInfo> routeInfos = ControllerManager.getRouteInfosForTest();
        // then:
        assertThat(routeInfos.size()).isEqualTo(4);
    }
}
