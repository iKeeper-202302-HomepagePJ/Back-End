package com.iKeeper.homepage.domain.auth.service;

import com.iKeeper.homepage.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import com.iKeeper.homepage.domain.user.entity.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByStudentId(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(Member user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(String.valueOf(user.getStudentId()))
                .password(user.getPassword())
                .roles(String.valueOf(user.getRole()))
                .build();
    }
}