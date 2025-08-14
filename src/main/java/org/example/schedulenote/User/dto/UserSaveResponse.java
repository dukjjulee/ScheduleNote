package org.example.schedulenote.User.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserSaveResponse {

    private final Long id;
    private final String author;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UserSaveResponse (
            Long id,
            String author,
            String email,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.author = author;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
