package io.github.dhar135.personal_blog.article.repository;

import io.github.dhar135.personal_blog.article.model.Article;

import java.util.List;

public interface ArticleRepository {

    Article save(Article article);

    Article findById(String id);

    List<Article> findAll();

    void deleteById(String id);

    Article update(Article article);

}
