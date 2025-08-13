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

    //속성
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String title;
    private String password;
    private String content;

    //생성자
    public Schedule(String author, String title, String password, String content ) {
        this.author = author;
        this.title = title;
        this.password = password;
        this.content = content;
    }
}
