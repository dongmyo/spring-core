package com.nhnent.study.springcore;

import com.nhnent.study.springcore.config.ApplicationContextConfiguration;
import com.nhnent.study.springcore.service.MemberService;
import com.nhnent.study.springcore.vo.Member;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);

        MemberService memberService = (MemberService) context.getBean("memberService");

        Member dongmyo1 = new Member();
        dongmyo1.setEmail("dongmyo1@nhnent.com");
        dongmyo1.setPassword("12345");
        dongmyo1.setName("dongmyo2");

        Member dongmyo2 = new Member();
        dongmyo2.setEmail("dongmyo2@nhnent.com");
        dongmyo2.setPassword("67890");
        dongmyo2.setName("dongmyo1");

        Member member1 = getOrCreateMember(memberService, dongmyo1);
        Member member2 = getOrCreateMember(memberService, dongmyo2);

        System.out.println(member1);
        System.out.println(member2);

        memberService.exchangeMemberName(member1, member2);
    }

    private static Member getOrCreateMember(MemberService memberService, Member member) throws Exception {
        Member newMember = memberService.getMember(member.getEmail(), member.getPassword());
        if (newMember == null) {
            newMember = memberService.createMember(member.getEmail(), member.getPassword(), member.getName());
        }

        return newMember;
    }

}
