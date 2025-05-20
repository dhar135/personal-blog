package io.github.dhar135.personal_blog.securingweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/edit").setViewName("edit");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/new").setViewName("new");
        registry.addViewController("/article").setViewName("article");
    }
}
