package org.example.schedulenote.User.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulenote.User.dto.UserResponse;
import org.example.schedulenote.User.dto.UserSaveRequest;
import org.example.schedulenote.User.dto.UserSaveResponse;
import org.example.schedulenote.User.entity.User;
import org.example.schedulenote.User.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //유저 생성
    @Transactional
    public UserSaveResponse save(UserSaveRequest request) {
        User user = new User(
                request.getAuthor(),
                request.getEmail()
        );

        User savedUser = userRepository.save(user);
        return new UserSaveResponse(
                savedUser.getId(),
                savedUser.getAuthor(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt()
        );
    }

    //유저 목록 조회
    @Transactional(readOnly = true)
    public List<UserResponse> findUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> dtos = new ArrayList<>();

        for(User user : users) {
            UserResponse userResponse = new UserResponse (
                    user.getId(),
                    user.getAuthor(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getModifiedAt()
            );
            dtos.add(userResponse);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public UserResponse findUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () ->new IllegalArgumentException("User를 찾을 수 없습니다.")
        );
        return new UserResponse(
                user.getId(),
                user.getAuthor(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    //유저 수정
    @Transactional
    public UserResponse updateUser(long userId, UserSaveRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User을 찾을 수 없습니다.")
        );

        if (!ObjectUtils.nullSafeEquals(user.getPassword(), request.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");

        user.UpdateUserInformation(request.getAuthor(), request.getEmail());
        return new UserResponse(
                user.getId(),
                user.getAuthor(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    //유저 삭제
    @Transactional
    public void deleteUser(long userId, String password) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User를 찾을 수 없습니다.")
        );

        if (!ObjectUtils.nullSafeEquals(user.getPassword(), password)) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }
    }
}
