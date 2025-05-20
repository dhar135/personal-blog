package io.github.dhar135.personal_blog.article.service;

import io.github.dhar135.personal_blog.article.model.Article;
import io.github.dhar135.personal_blog.article.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    private ArticleServiceImpl articleService;
    private LocalDateTime testDateTime;

    @BeforeEach
    void setUp() {
        articleService = new ArticleServiceImpl(articleRepository);
        testDateTime = LocalDateTime.now();
    }

    private Article createTestArticle(String id) {
        return new Article(id, "Test Title", "Test Content", testDateTime);
    }

    @Test
    void findById_WithValidId_ReturnsArticle() {
        // Arrange
        String validId = "123";
        Article expectedArticle = createTestArticle(validId);
        when(articleRepository.findById(validId)).thenReturn(expectedArticle);

        // Act
        Article result = articleService.findById(validId);

        // Assert
        assertNotNull(result);
        assertEquals(validId, result.getId());
        verify(articleRepository).findById(validId);
    }

    @Test
    void findById_WithNullId_ThrowsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> articleService.findById(null));
        verify(articleRepository, never()).findById(any());
    }

    @Test
    void findById_WithEmptyId_ThrowsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> articleService.findById(""));
        verify(articleRepository, never()).findById(any());
    }

    @Test
    void findAll_ReturnsAllArticles() {
        // Arrange
        List<Article> expectedArticles = Arrays.asList(
                createTestArticle("1"),
                createTestArticle("2"));
        when(articleRepository.findAll()).thenReturn(expectedArticles);

        // Act
        List<Article> result = articleService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedArticles.size(), result.size());
        verify(articleRepository).findAll();
    }

    @Test
    void save_WithValidArticle_SavesAndReturnsArticle() {
        // Arrange
        Article article = createTestArticle("123");
        when(articleRepository.save(article)).thenReturn(article);

        // Act
        Article result = articleService.save(article);

        // Assert
        assertNotNull(result);
        assertEquals(article.getId(), result.getId());
        verify(articleRepository).save(article);
    }

    @Test
    void save_WithNullId_ThrowsIllegalArgumentException() {
        // Arrange
        Article article = createTestArticle("123");
        // Use reflection to set id to null for testing
        try {
            java.lang.reflect.Field field = Article.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(article, null);
        } catch (Exception e) {
            fail("Failed to set id to null for testing", e);
        }

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> articleService.save(article));
        verify(articleRepository, never()).save(any());
    }

    @Test
    void save_WithEmptyId_ThrowsIllegalArgumentException() {
        // Arrange
        Article article = createTestArticle("123");
        // Use reflection to set id to empty for testing
        try {
            java.lang.reflect.Field field = Article.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(article, "");
        } catch (Exception e) {
            fail("Failed to set id to empty for testing", e);
        }

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> articleService.save(article));
        verify(articleRepository, never()).save(any());
    }

    @Test
    void delete_WithValidId_DeletesArticle() {
        // Arrange
        String validId = "123";

        // Act
        articleService.delete(validId);

        // Assert
        verify(articleRepository).deleteById(validId);
    }

    @Test
    void delete_WithNullId_ThrowsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> articleService.delete(null));
        verify(articleRepository, never()).deleteById(any());
    }

    @Test
    void delete_WithEmptyId_ThrowsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> articleService.delete(""));
        verify(articleRepository, never()).deleteById(any());
    }
}