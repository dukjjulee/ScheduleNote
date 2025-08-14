package org.example.schedulenote.User.dto;

import lombok.Getter;

@Getter
public class UserUpdateRequest {

    private String author;
    private String email;
    private String password;
}
