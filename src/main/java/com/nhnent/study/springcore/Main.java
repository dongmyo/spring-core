package com.nhnent.study.springcore;

import com.nhnent.study.springcore.config.ApplicationContextConfiguration;
import com.nhnent.study.springcore.dao.MemberDao;
import com.nhnent.study.springcore.vo.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);

        MemberDao memberDao = context.getBean(MemberDao.class);

        Member member = memberDao.exist("dongmyo@nhnent.com", "12345");
        if (member == null) {
            member = new Member();
            member.setEmail("dongmyo@nhnent.com");
            member.setPassword("12345");
            member.setName("dongmyo");
            member.setCreatedDate(new Date());
            member.setModifiedDate(new Date());

            try {
                memberDao.insert(member);
            }
            catch (Exception e) {
                LOGGER.error("{}", e);
            }

            System.out.println("dongmyo NOT exists");
        }
        else {
            System.out.println(member.getName());
        }
    }

}
