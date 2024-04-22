package com.iKeeper.homepage.domain.user.service;

import com.iKeeper.homepage.domain.user.dao.MemberInfo;
import com.iKeeper.homepage.domain.user.dao.MemberRepository;
import com.iKeeper.homepage.domain.user.dto.request.MemberRequest;
import com.iKeeper.homepage.domain.user.entity.Member;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MemberRepository memberRepository;

    public Optional<MemberInfo> searchMemberInfo(String studentId) {

        Optional<MemberInfo> searchMemberInfo = memberRepository.findMemberInfoByStudentId(studentId);
        return searchMemberInfo;
    }

    @Transactional
    public Optional<MemberInfo> updateMemberInfo(String studentId, MemberRequest memberRequest) {
        Member member = memberRepository.findByStudentId(studentId)
                .orElseThrow(() -> new CustomException("해당 학번의 유저가 존재하지 않습니다.", ErrorCode.USER_MEMBER_NOT_FOUND));

        member.updateName(memberRequest.getName());
        member.updatePnumber(memberRequest.getPnumber());
        member.updateBirth(memberRequest.getBirth());
        member.updateEmail(memberRequest.getEmail());
        member.updateField(memberRequest.getField());
        member.updateStatus(memberRequest.getStatus());
        member.updateGrade(memberRequest.getGrade());
        member.updateMajor1(memberRequest.getMajor1());
        member.updateMajor2(memberRequest.getMajor2());
        member.updateMajor3(memberRequest.getMajor3());
        member.updateMinor(memberRequest.getMinor());

        Optional<MemberInfo> searchMemberInfo = memberRepository.findMemberInfoByStudentId(studentId);
        return searchMemberInfo;
    }

    public String deleteAccount(String studentId) {
        memberRepository.deleteById(studentId);
        return "success";
    }
}
