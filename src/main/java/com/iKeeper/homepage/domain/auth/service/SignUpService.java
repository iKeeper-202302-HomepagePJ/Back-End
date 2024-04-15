package com.iKeeper.homepage.domain.auth.service;

import com.iKeeper.homepage.domain.user.dao.UserRepository;
import com.iKeeper.homepage.domain.user.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;

    public Member saveUser(Member member) {

        validateDuplicateUser(member);
        return userRepository.save(member);
    }

    private void validateDuplicateUser(Member member) {
        Member findMember = userRepository.findAllByStudentId(member.getStudentId());
        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
