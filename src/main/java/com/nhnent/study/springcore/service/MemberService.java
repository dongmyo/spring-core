package com.nhnent.study.springcore.service;

import com.nhnent.study.springcore.vo.Member;

public interface MemberService {
    Member createMember(String email, String password, String name);

    Member getMember(String email, String password) throws Exception;

    void exchangeMemberName(Member member1, Member member2) throws Exception;

}
