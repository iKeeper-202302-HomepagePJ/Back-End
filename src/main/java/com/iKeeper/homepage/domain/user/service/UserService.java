package com.iKeeper.homepage.domain.user.service;

import com.iKeeper.homepage.domain.user.dao.UserRepository;
import com.iKeeper.homepage.domain.user.dto.request.UserRequest;
import com.iKeeper.homepage.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> searchUser(String student) {

        Optional<User> searchUser = userRepository.findByStudent(student);
        return searchUser;
    }

    @Transactional
    public String updateUser(String student, UserRequest userRequest, PasswordEncoder passwordEncoder) {
        User user = userRepository.findByStudent(student)
                .orElseThrow(() -> new RuntimeException("해당 학번의 유저가 존재하지 않습니다."));

        user.updateName(userRequest.getName());
        user.updatePnumber(userRequest.getPnumber());
        user.updateBirth(userRequest.getBirth());
        user.updateEmail(userRequest.getEmail());
        user.updatePassword(passwordEncoder.encode(userRequest.getPassword()));
        user.updateField(userRequest.getField());
        user.updateStatus(userRequest.getStatus());
        user.updateGrade(userRequest.getGrade());
        return student;
    }

    public void deleteUser(User student) {
        userRepository.delete(student);
    }
}
