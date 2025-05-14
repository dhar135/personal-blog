package io.github.dhar135.personal_blog.article.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dhar135.personal_blog.article.model.Article;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Repository implementation that stores articles as JSON files in the filesystem
 */
@Repository
public class FileSystemArticleRepository implements ArticleRepository {

    private final Path storageDirectory;
    private final ObjectMapper objectMapper;

    /**
     * Creates a new FileSystemArticleRepository
     *
     * @param storageDirectory Path to directory where article files will be stored
     * @param objectMapper     ObjectMapper for JSON serialization/deserialization
     * @throws RuntimeException if storage directory cannot be created
     */
    public FileSystemArticleRepository(@Value("${blog.articles.storage-path}") Path storageDirectory, ObjectMapper objectMapper) {
        this.storageDirectory = Paths.get(storageDirectory.toUri());
        this.objectMapper = objectMapper;

        // Create a directory if it doesn't exist
        try {
            Files.createDirectories(this.storageDirectory);
            System.out.println("Storage directory verified/created at: " + this.storageDirectory.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Could not create storage directory: " + this.storageDirectory.toAbsolutePath());
            throw new RuntimeException("Could not create storage directory: " + e.getMessage());
        }
    }

    /**
     * Saves an article to a JSON file
     *
     * @param article Article to save
     * @return The saved article
     * @throws RuntimeException if article cannot be written to file
     */
    @Override
    public Article save(Article article) {

        String fileName = article.getId() + ".json";
        Path file = storageDirectory.resolve(fileName);

        try {
            objectMapper.writeValue(file.toFile(), article);
        } catch (IOException e) {
            throw new RuntimeException("Error writing article to file: " + file + " - " + e.getMessage(), e);
        }
        return article;
    }

    /**
     * Finds an article by its ID
     *
     * @param id ID of article to find
     * @return Article if found, null if not found
     * @throws RuntimeException if article file cannot be read
     */
    @Override
    public Article findById(long id) {

        String fileName = id + ".json";
        Path file = storageDirectory.resolve(fileName);

        if (Files.exists(file)) {
            try {
                return objectMapper.readValue(file.toFile(), Article.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    /**
     * Returns all articles
     *
     * @return List of all articles
     */
    @Override
    public List<Article> findAll() {

        return List.of();
    }

    /**
     * Deletes an article by ID
     *
     * @param id ID of article to delete
     */
    @Override
    public void deleteById(String id) {

    }
}