package com.nhnent.study.springcore.service;

import com.nhnent.study.springcore.dao.MemberDao;
import com.nhnent.study.springcore.vo.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("memberService")
public class MemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberService.class);


    @Autowired
    MemberDao memberDao;


    public Member createMember(String email, String password, String name) {
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);
        member.setName(name);
        member.setCreatedDate(new Date());
        member.setModifiedDate(new Date());

        try {
            memberDao.insert(member);
        }
        catch (Exception e) {
            LOGGER.error("{}", e);
        }

        return member;
    }

    public Member getMember(String email, String password) {
        return memberDao.exist(email, password);
    }

}
