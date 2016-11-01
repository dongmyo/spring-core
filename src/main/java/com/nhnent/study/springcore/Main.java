package com.nhnent.study.springcore;

import com.nhnent.study.springcore.config.ApplicationContextConfiguration;
import com.nhnent.study.springcore.service.MemberService;
import com.nhnent.study.springcore.vo.Member;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);

        MemberService memberService = (MemberService) context.getBean("memberService");

        String email = "dongmyo1@nhnent.com";
        String password = "12345";
        String name = "dongmyo1";

        Member member = memberService.getMember(email, password);
        if (member == null) {
            System.out.println("dongmyo NOT exists");
            memberService.createMember(email, password, name);
        }
        else {
            System.out.println(member.getName());
        }
    }

}
