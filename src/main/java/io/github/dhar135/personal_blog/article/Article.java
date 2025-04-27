package io.github.dhar135.personal_blog.article;

import java.time.LocalDateTime;

public class Article {

    private final String id;
    private String title;
    private String content;
    private LocalDateTime publishDate;

    public Article(String id, String title, String content, LocalDateTime publishDate) {

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
        if (publishDate == null || publishDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Article publish date cannot be before now");
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
