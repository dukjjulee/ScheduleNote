package org.example.schedulenote.Schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequest {

    private String title;
    private String content;
    private String author;
    private String password;
}
