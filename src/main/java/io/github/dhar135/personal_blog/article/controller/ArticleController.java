package io.github.dhar135.personal_blog.article.controller;

import io.github.dhar135.personal_blog.article.model.Article;
import io.github.dhar135.personal_blog.article.service.ArticleServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class ArticleController {

    private final ArticleServiceImpl articleService;

    public ArticleController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    // Home Page
    @GetMapping({"/", "/home"})
    public String showHomePage(Model model) {
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);

        return "home";
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model, Principal principal, Authentication authentication) {
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);
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
        return "admin";
    }

    @PostMapping("/create_article")
    public String createArticle(@ModelAttribute("article") Article article, RedirectAttributes redirectAttributes) {
        try {
            articleService.save(article);
            redirectAttributes.addFlashAttribute("message", "Successfully created a new article");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin";
    }

    // Show create article form
    @GetMapping("/new")
    public String showCreateArticleForm(Model model) {
        model.addAttribute("article", new Article());
        return "new"; // This will be the name of our new HTML template
    }

    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable("id") String id, Model model) {
        model.addAttribute("article", articleService.findById(id));
        return "article";
    }

    @GetMapping("/edit/{id}")
    public String showEditArticleForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("article", articleService.findById(id));
        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteArticle(@PathVariable("id") String id, Model model) {
        model.addAttribute("article", articleService.findById(id));
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteArticle(@ModelAttribute("article") Article article, RedirectAttributes redirectAttributes) {
        try {
            articleService.delete(article.getId());
            redirectAttributes.addFlashAttribute("message", "Successfully deleted an article");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin";
    }



}
