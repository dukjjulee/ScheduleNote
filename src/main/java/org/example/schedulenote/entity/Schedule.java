package org.example.schedulenote.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Schedule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String title;
    private String password;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Schedule(String author, String title, String password, String content ) {
        this.author = author;
        this.title = title;
        this.password = password;
        this.content = content;
    }

    public void updateTitleAndAuThor(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
