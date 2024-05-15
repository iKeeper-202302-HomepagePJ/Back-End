package com.iKeeper.homepage.domain.user.service;

import com.iKeeper.homepage.domain.user.dao.MemberRepository;
import com.iKeeper.homepage.domain.user.dto.response.MemberListResponse;
import com.iKeeper.homepage.domain.user.entity.Member;
import com.iKeeper.homepage.domain.user.entity.UserRole;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminMemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public List<MemberListResponse> memberList() {
        return memberRepository.findAllDesc().stream()
                .map(MemberListResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public String updateRoleUser(String studentId) {

        Member member = memberRepository.findById(studentId)
                .orElseThrow(() -> new CustomException("해당 학번의 유저가 존재하지 않습니다.", ErrorCode.USER_INVALID_VALUE));
        member.updateRole(UserRole.USER);
        return "success";
    }

    @Transactional
    public String updateRoleAdmin(String studentId) {

        Member member = memberRepository.findById(studentId)
                .orElseThrow(() -> new CustomException("해당 학번의 유저가 존재하지 않습니다.", ErrorCode.USER_INVALID_VALUE));
        member.updateRole(UserRole.ADMIN);
        return "success";
    }

    public void deleteMember(String studentId) {
        memberRepository.deleteById(studentId);
    }
}