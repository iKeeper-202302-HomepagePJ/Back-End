package com.iKeeper.homepage.domain.user.dao;

import com.iKeeper.homepage.domain.user.dao.mapping.MemberInfo;
import com.iKeeper.homepage.domain.user.dao.mapping.MemberList;
import com.iKeeper.homepage.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    @Query("select p from Post p ORDER BY p.id DESC")
    List<Member> findAllDesc();

    Optional<MemberList> findMemberListByStudentId(String studentId);

    Optional<MemberInfo> findMemberInfoByStudentId(String studentId);

    Member findAllByStudentId(String studentId);
}