package io.github.dhar135.personal_blog.article.controller;

import io.github.dhar135.personal_blog.article.model.Article;
import io.github.dhar135.personal_blog.article.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // Home Page
    @GetMapping("/home")
    public String showHomePage(Model model) {
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "home";
    }

}
