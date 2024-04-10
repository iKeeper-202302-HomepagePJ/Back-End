package com.iKeeper.homepage.domain.auth.service;

import com.iKeeper.homepage.domain.auth.dao.UserRepository;
import com.iKeeper.homepage.domain.auth.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserSignUpService {

    private final UserRepository userRepository;

    public User saveUser(User user) {

        validateDuplicateUser(user);
        return userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
        User findUser = userRepository.findAllByStudent(user.getStudent());
        if(findUser != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
