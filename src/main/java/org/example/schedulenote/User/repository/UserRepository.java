package org.example.schedulenote.User.repository;

import org.example.schedulenote.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
