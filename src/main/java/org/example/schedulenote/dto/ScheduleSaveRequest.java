package org.example.schedulenote.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleSaveRequest {

    private Long id;
    private String title;
    private String content;
    private String author;
    private String password;
}
