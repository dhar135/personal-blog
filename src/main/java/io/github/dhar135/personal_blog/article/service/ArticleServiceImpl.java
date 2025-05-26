package io.github.dhar135.personal_blog.article.service;

import io.github.dhar135.personal_blog.article.model.Article;
import io.github.dhar135.personal_blog.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * The ArticleService class provides functionality for managing and handling articles.
 * It serves as a service layer for performing operations related to articles,
 * such as creating, retrieving, updating, and deleting articles.
 * This class encapsulates the business logic associated with article management.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

 final ArticleRepository articleRepository;

 public ArticleServiceImpl(ArticleRepository articleRepository) {
     this.articleRepository = articleRepository;
 }

    /**
     * @param id
     * @return
     */
    @Override
    public Article findById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or empty");
        }
        return articleRepository.findById(id);
    }

    /**
     * @return
     */
    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    /**
     * @param article
     * @return
     */
    @Override
    public Article save(Article article) {
        try {
            article.setId(UUID.randomUUID().toString());
            article.setPublishDate(LocalDateTime.now());
            if (article.getId() == null || article.getId().isEmpty()) {
                throw new IllegalArgumentException("id cannot be null or empty");
            }
            return articleRepository.save(article);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param id
     */
    @Override
    public void delete(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or empty");
        }
        articleRepository.deleteById(id);
    }



}
