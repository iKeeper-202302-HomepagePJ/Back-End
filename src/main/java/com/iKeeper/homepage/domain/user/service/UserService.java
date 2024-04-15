package com.iKeeper.homepage.domain.user.service;

import com.iKeeper.homepage.domain.user.dao.UserRepository;
import com.iKeeper.homepage.domain.user.dto.request.UserRequest;
import com.iKeeper.homepage.domain.user.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<Member> searchUser(String studentId) {

        Optional<Member> searchUser = userRepository.findByStudentId(studentId);
        return searchUser;
    }

    @Transactional
    public String updateUser(String studentId, UserRequest userRequest, PasswordEncoder passwordEncoder) {
        Member member = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("해당 학번의 유저가 존재하지 않습니다."));

        member.updateName(userRequest.getName());
        member.updatePnumber(userRequest.getPnumber());
        member.updateBirth(userRequest.getBirth());
        member.updateEmail(userRequest.getEmail());
        member.updateField(userRequest.getField());
        member.updateStatus(userRequest.getStatus());
        member.updateGrade(userRequest.getGrade());
        return studentId;
    }

    public void deleteUser(Member studentId) {
        userRepository.delete(studentId);
    }
}
