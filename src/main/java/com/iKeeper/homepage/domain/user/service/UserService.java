package com.iKeeper.homepage.domain.user.service;

import com.iKeeper.homepage.domain.post.dao.CommentRepository;
import com.iKeeper.homepage.domain.post.dao.PostRepository;
import com.iKeeper.homepage.domain.post.dao.mapping.PostList;
import com.iKeeper.homepage.domain.post.dto.response.PostListResponse;
import com.iKeeper.homepage.domain.post.entity.Comment;
import com.iKeeper.homepage.domain.user.dao.mapping.MemberInfo;
import com.iKeeper.homepage.domain.user.dao.MemberRepository;
import com.iKeeper.homepage.domain.user.dto.request.MemberRequest;
import com.iKeeper.homepage.domain.user.dto.response.MemberListResponse;
import com.iKeeper.homepage.domain.user.entity.Member;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Optional<MemberInfo> searchMemberInfo(String studentId) {

        Optional<MemberInfo> searchMemberInfo = memberRepository.findMemberInfoByStudentId(studentId);
        return searchMemberInfo;
    }

    public List<PostList> searchMyPost(String studentId) {

        List<PostList> searchMyPost = postRepository.findByPostStudentId(studentId);
        return searchMyPost;
    }

    public List<Comment> searchMyComment(String studentId) {

        List<Comment> searchMyComment = commentRepository.findByCommentStudentId(studentId);
        return searchMyComment;
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
