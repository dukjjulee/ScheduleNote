package org.example.schedulenote.Schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedulenote.common.Base;

@Getter
@Entity
@NoArgsConstructor
public class Schedule extends Base {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String title;
    private String password;
    private String content;

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
