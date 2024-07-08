package com.iKeeper.homepage.domain.user.service;

import com.iKeeper.homepage.domain.post.dao.PostRepository;
import com.iKeeper.homepage.domain.post.dto.response.PostListResponse;
import com.iKeeper.homepage.domain.user.dao.*;
import com.iKeeper.homepage.domain.user.dto.request.MemberRequest;
import com.iKeeper.homepage.domain.user.dto.response.MemberInfoResponse;
import com.iKeeper.homepage.domain.user.dto.response.MemberListResponse;
import com.iKeeper.homepage.domain.user.entity.*;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final MajorRepository majorRepository;
    private final FieldRepository fieldRepository;
    private final StatusRepository statusRepository;
    private final GradeRepository gradeRepository;

    public List<Field> searchAllField() {
        return fieldRepository.findAll();
    }

    public List<Status> searchAllStatus() {
        return statusRepository.findAll();
    }

    public List<Grade> searchAllGrade() {
        return gradeRepository.findAll();
    }

    public List<Major> searchAllMajor() {
        return majorRepository.findAll();
    }

    @Transactional
    public Optional<MemberListResponse> searchMember(String studentId) {
        return memberRepository.findById(studentId)
                .map(MemberListResponse::new);
    }

    @Transactional
    public Optional<MemberInfoResponse> summaryMember(String studentId) {
        return memberRepository.findById(studentId)
                .map(MemberInfoResponse::new);
    }

    @Transactional
    public Page<PostListResponse> getMyPostList(String studentId, int page) {

        Pageable pageable = PageRequest.of(page - 1, 15);
        return postRepository.findByPostStudentId(studentId, pageable)
                .map(PostListResponse::new);
    }

    /* public List<Comment> searchMyComment(String studentId) {

        List<Comment> searchMyComment = commentRepository.findByCommentStudentId(studentId);
        return searchMyComment;
    } */

    @Transactional
    public Optional<Member> updateMemberInfo(String studentId, MemberRequest memberRequest) {
        Member member = memberRepository.findById(studentId)
                .orElseThrow(() -> new CustomException("해당 학번의 유저가 존재하지 않습니다.", ErrorCode.USER_MEMBER_NOT_FOUND));

        member.updateMemberInfo(memberRequest.getName(), member.getPnumber(), member.getBirth(), memberRequest.getField(),
                memberRequest.getStatus(), memberRequest.getGrade(), memberRequest.getMajor1(), memberRequest.getMajor2(),
                memberRequest.getMajor3(), memberRequest.getMinor());
        return memberRepository.findById(studentId);
    }

    public String deleteAccount(String studentId) {
        memberRepository.deleteById(studentId);
        return "success";
    }
}
