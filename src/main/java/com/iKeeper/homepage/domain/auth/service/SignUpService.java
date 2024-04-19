package com.iKeeper.homepage.domain.auth.service;

import com.iKeeper.homepage.domain.user.dao.UserRepository;
import com.iKeeper.homepage.domain.user.entity.Member;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
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
            throw new CustomException("해당 학번의 회원이 이미 존재합니다.", ErrorCode.AUTH_MEMBER_DUPLICATE);
        }
    }
}
