package com.iKeeper.homepage.domain.user.service;

import com.iKeeper.homepage.domain.user.dao.MemberRepository;
import com.iKeeper.homepage.domain.user.dto.response.MemberListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminMemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberListResponse> memberList() {
        return memberRepository.findAllDesc().stream()
                .map(MemberListResponse::new)
                .collect(Collectors.toList());
    }

    public void deleteMember(String studentId) {
        memberRepository.deleteById(studentId);
    }
}