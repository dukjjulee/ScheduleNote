package org.example.schedulenote.Schedule.dto;

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
