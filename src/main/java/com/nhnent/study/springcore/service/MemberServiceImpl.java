package com.nhnent.study.springcore.service;

import com.nhnent.study.springcore.repository.MemberRepository;
import com.nhnent.study.springcore.vo.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);


    @Autowired
    MemberRepository memberRepository;


    @Override
    public Member createMember(String email, String password, String name) {
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);
        member.setName(name);
        member.setCreatedDate(new Date());
        member.setModifiedDate(new Date());

        try {
            memberRepository.save(member);
        }
        catch (Exception e) {
            LOGGER.error("{}", e);
        }

        return member;
    }

    @Override
    public Member getMember(String email, String password) throws Exception {
        Member member = memberRepository.findByEmailAndPassword(email, password);
        if (member == null) {
            return null;
        }

        return memberRepository.findOne(member.getNo());
    }

    @Override
    public void exchangeMemberName(Member member1, Member member2) throws Exception {
        String tempName = member1.getName();
        member1.setName(member2.getName());
        member2.setName(tempName);

//            member1.setName("dongmyo1");
//            member2.setName("dongmyo2");

        memberRepository.update(member1);
        memberRepository.update(member2);
    }

}
