package org.example.schedulenote.User.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedulenote.common.Base;

@Getter
@Entity
@NoArgsConstructor
public class User extends Base {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String email;
    private String password;

    public User(String author, String email, String password) {
        this.author = author;
        this.email = email;
        this.password = password;

    }

    public void UpdateUserInformation(String author, String email, String password) {
        this.author = author;
        this.email = email;
        this.password = password;

    }
}
