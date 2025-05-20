package io.github.dhar135.personal_blog.article.controller;

import io.github.dhar135.personal_blog.article.service.ArticleService;
import org.springframework.stereotype.Controller;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

}
