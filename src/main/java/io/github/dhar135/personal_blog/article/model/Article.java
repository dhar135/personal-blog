package io.github.dhar135.personal_blog.article.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Article {


    private String id;
    private String title;
    private String content;
    private LocalDateTime publishDate;

    @JsonCreator
    public Article() {
    }

    @JsonCreator
    public Article(
            @JsonProperty("id") String id,
            @JsonProperty("title") String title,
            @JsonProperty("content") String content,
            @JsonProperty("publishDate") LocalDateTime publishDate) {

        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Article id cannot be null or empty");
        }

        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Article title cannot be null or empty");
        }

        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Article content cannot be null or empty");
        }

        if (publishDate == null) {
            throw new IllegalArgumentException("Article publish date cannot be null");
        }

        this.id = id;
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Article id cannot be null or empty");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Article title cannot be null or empty");
        }
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Article content cannot be null or empty");
        }
        this.content = content;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        if (publishDate == null) {
            throw new IllegalArgumentException("Article publish date cannot be null");
        }
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }
}
