package com.iKeeper.homepage.domain.user.service;

import com.iKeeper.homepage.domain.user.dao.MajorRepository;
import com.iKeeper.homepage.domain.user.dao.MemberRepository;
import com.iKeeper.homepage.domain.user.dao.ScoreRepository;
import com.iKeeper.homepage.domain.user.dto.request.ScoreRequest;
import com.iKeeper.homepage.domain.user.dto.response.MemberListResponse;
import com.iKeeper.homepage.domain.user.dto.response.StudentIdResponse;
import com.iKeeper.homepage.domain.user.entity.Major;
import com.iKeeper.homepage.domain.user.entity.Member;
import com.iKeeper.homepage.domain.user.entity.Score;
import com.iKeeper.homepage.domain.user.entity.UserRole;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminMemberService {

    private final MemberRepository memberRepository;
    private final ScoreRepository scoreRepository;
    private final MajorRepository majorRepository;

    @Transactional
    public List<MemberListResponse> memberList() {
        return memberRepository.findAllDesc().stream()
                .map(MemberListResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<StudentIdResponse> studentIdList() {
        return memberRepository.findAll().stream()
                .map(StudentIdResponse::new)
                .collect(Collectors.toList());
    }

    public List<Score> memberScoreList() {
        return scoreRepository.findAll();
    }

    public Score createScore(Score score) {
        return scoreRepository.save(score);
    }

    public Major createMajor(Major major) {
        return majorRepository.save(major);
    }

    @Transactional
    public String updateRoleUser(String studentId) {

        Member member = memberRepository.findById(studentId)
                .orElseThrow(() -> new CustomException("해당 학번의 유저가 존재하지 않습니다.", ErrorCode.USER_INVALID_VALUE));
        member.updateRole(UserRole.USER);
        return "success";
    }

    @Transactional
    public void updateRoleAdmin(String studentId) {
        Member member = memberRepository.findById(studentId)
                .orElseThrow(() -> new CustomException("해당 학번의 유저가 존재하지 않습니다.", ErrorCode.USER_INVALID_VALUE));

        member.updateRole(UserRole.ADMIN);
    }

    @Transactional
    public void grantWarning(String studentId) {
        Member member = memberRepository.findById(studentId)
                .orElseThrow(() -> new CustomException("해당 학번의 유저가 존재하지 않습니다.", ErrorCode.USER_INVALID_VALUE));

        member.updateWarning(Boolean.TRUE);
    }

    @Transactional
    public Optional<Score> updateScore(String studentId, ScoreRequest scoreRequest) {
        Score score = scoreRepository.findById(studentId)
                .orElseThrow(() -> new CustomException("해당 학번의 유저가 존재하지 않습니다.", ErrorCode.USER_MEMBER_NOT_FOUND));

        score.updateScore(scoreRequest.getMain(), scoreRequest.getField(), scoreRequest.getActivity(),
                scoreRequest.getEtc(), scoreRequest.getSum());
        return scoreRepository.findById(studentId);
    }

    @Transactional
    public void resetScore(String studentId) {
        Score score = scoreRepository.findById(studentId)
                .orElseThrow(() -> new CustomException("해당 학번의 유저가 존재하지 않습니다.", ErrorCode.USER_MEMBER_NOT_FOUND));

        score.updateScore((short) 0, (short) 0, (short) 0, (short) 0, (short) 0);
    }

    public void deleteMember(String studentId) {
        memberRepository.deleteById(studentId);
    }

    public void deleteScore(String studentId) {
        scoreRepository.deleteById(studentId);
    }
}