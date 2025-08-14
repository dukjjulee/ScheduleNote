package org.example.schedulenote.User.dto;

import lombok.Getter;

@Getter
public class UserSaveRequest {

    private Long id;
    private String author;
    private String email;
}
