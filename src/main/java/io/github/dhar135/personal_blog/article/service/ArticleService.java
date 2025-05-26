package io.github.dhar135.personal_blog.article.service;

import io.github.dhar135.personal_blog.article.model.Article;

import java.util.List;

public interface ArticleService {
    Article findById(String id);
    List<Article> findAll();
    Article save(Article article);
    void delete(String id);
    Article update(Article article);
}
