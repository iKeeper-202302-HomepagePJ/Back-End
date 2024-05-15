package com.iKeeper.homepage.domain.auth.service;

import com.iKeeper.homepage.domain.user.dao.MemberRepository;
import com.iKeeper.homepage.global.error.CustomException;
import lombok.RequiredArgsConstructor;
import com.iKeeper.homepage.domain.user.entity.Member;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.iKeeper.homepage.global.error.ErrorCode.AUTH_MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new CustomException("해당 이름의 회원을 찾을 수 없습니다.", AUTH_MEMBER_NOT_FOUND));
    }

    private UserDetails createUserDetails(Member member) {
        return User.builder()
                .username(member.getStudentId())
                .password(member.getPassword())
                .roles(String.valueOf(member.getRole()))
                .build();
    }
}