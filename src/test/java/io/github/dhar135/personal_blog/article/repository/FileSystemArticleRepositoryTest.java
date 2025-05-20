package io.github.dhar135.personal_blog.article.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dhar135.personal_blog.article.model.Article;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemArticleRepositoryTest {

    @TempDir
    Path tempDir;

    private FileSystemArticleRepository repository;
    private ObjectMapper objectMapper;
    private Article testArticle;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        repository = new FileSystemArticleRepository(tempDir, objectMapper);
 
        // Create a test article
        testArticle = new Article(
                "1",
                "Test Article",
                "Test Content",
                LocalDateTime.now().plusDays(1));
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clean up all files in the temp directory
        Files.list(tempDir).forEach(path -> {
            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void save_ShouldCreateNewArticleFile() {
        // When
        Article savedArticle = repository.save(testArticle);

        // Then
        assertTrue(Files.exists(tempDir.resolve("1.json")));
        assertEquals(testArticle.getId(), savedArticle.getId());
        assertEquals(testArticle.getTitle(), savedArticle.getTitle());
        assertEquals(testArticle.getContent(), savedArticle.getContent());
    }

    @Test
    void save_ShouldOverwriteExistingArticle() throws IOException {
        // Given
        repository.save(testArticle);
        testArticle.setTitle("Updated Title");

        // When
        Article updatedArticle = repository.save(testArticle);

        // Then
        assertEquals("Updated Title", updatedArticle.getTitle());
        assertEquals(1, Files.list(tempDir).count());
    }

    @Test
    void findById_ShouldReturnArticle_WhenFileExists() {
        // Given
        repository.save(testArticle);

        // When
        Article found = repository.findById("1");

        // Then
        assertNotNull(found);
        assertEquals(testArticle.getId(), found.getId());
        assertEquals(testArticle.getTitle(), found.getTitle());
        assertEquals(testArticle.getContent(), found.getContent());
    }

    @Test
    void findById_ShouldReturnNull_WhenFileDoesNotExist() {
        // When
        Article found = repository.findById("999");

        // Then
        assertNull(found);
    }

    @Test
    void findAll_ShouldReturnAllArticles() {
        // Given
        Article article1 = new Article(
                "1",
                "Article 1",
                "Content 1",
                LocalDateTime.now().plusDays(1));

        Article article2 = new Article(
                "2",
                "Article 2",
                "Content 2",
                LocalDateTime.now().plusDays(1));

        repository.save(article1);
        repository.save(article2);

        // When
        List<Article> articles = repository.findAll();

        // Then
        assertEquals(2, articles.size());
        assertTrue(articles.stream().anyMatch(a -> a.getId().equals("1")));
        assertTrue(articles.stream().anyMatch(a -> a.getId().equals("2")));
    }

    @Test
    void findAll_ShouldReturnEmptyList_WhenNoArticlesExist() {
        // When
        List<Article> articles = repository.findAll();

        // Then
        assertTrue(articles.isEmpty());
    }

    @Test
    void deleteById_ShouldRemoveArticleFile() {
        // Given
        repository.save(testArticle);
        assertTrue(Files.exists(tempDir.resolve("1.json")));

        // When
        repository.deleteById("1");

        // Then
        assertFalse(Files.exists(tempDir.resolve("1.json")));
    }

    @Test
    void deleteById_ShouldNotThrowException_WhenFileDoesNotExist() {
        // When/Then
        assertDoesNotThrow(() -> repository.deleteById("999"));
    }

    @Test
    void constructor_ShouldCreateStorageDirectory_WhenItDoesNotExist() {
        // Given
        Path newDir = tempDir.resolve("new-dir");

        // When
        FileSystemArticleRepository newRepository = new FileSystemArticleRepository(newDir, objectMapper);

        // Then
        assertTrue(Files.exists(newDir));
        assertTrue(Files.isDirectory(newDir));
    }

    @Test
    void save_ShouldThrowException_WhenFileSystemIsReadOnly() throws IOException {
        // Given
        Path readOnlyDir = tempDir.resolve("readonly");
        Files.createDirectory(readOnlyDir);
        readOnlyDir.toFile().setReadOnly();

        FileSystemArticleRepository readOnlyRepository = new FileSystemArticleRepository(readOnlyDir, objectMapper);

        // When/Then
        assertThrows(RuntimeException.class, () -> readOnlyRepository.save(testArticle));
    }
}