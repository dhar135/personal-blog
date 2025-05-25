package io.github.dhar135.personal_blog.article.controller;

import io.github.dhar135.personal_blog.article.model.Article;
import io.github.dhar135.personal_blog.article.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // Home Page
    @GetMapping({"/", "/home"})
    public String showHomePage(Model model, HttpServletRequest request, Principal principal, Authentication authentication) {
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        model.addAttribute("sessionId", request.getSession().getId());

        if (principal != null) {
            model.addAttribute("isLoggedIn", true);
            // You can get more details from Authentication if needed
            if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
                model.addAttribute("username", userDetails.getUsername());
            } else {
                model.addAttribute("username", principal.getName());
            }
        } else {
            model.addAttribute("isLoggedIn", false);
        }
        return "home";

    }

}
