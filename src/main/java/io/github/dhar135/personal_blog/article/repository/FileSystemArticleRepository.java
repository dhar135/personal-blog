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


@Repository
public class FileSystemArticleRepository implements ArticleRepository {

    private final Path storageDirectory;
    private final ObjectMapper objectMapper;

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

    @Override
    public Article findById(long id) {
        return null;
    }

    @Override
    public List<Article> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(String id) {

    }
}
