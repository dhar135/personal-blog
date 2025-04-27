package io.github.dhar135.personal_blog.article.repository;

import io.github.dhar135.personal_blog.article.model.Article;

import java.util.List;

public interface ArticleRepository {

    public Article save(Article article);

    public Article findById(long id);

    public List<Article> findAll();

    public void deleteById(String id);

}
