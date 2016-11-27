package com.nhnent.study.springcore.repository;

import com.nhnent.study.springcore.vo.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

public class MemberRepositoryImpl implements MemberRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;


    public int update(Member member) throws Exception {
        // TODO: exception이 발생하면?
        if (Objects.equals(member.getEmail(), "dongmyo2@nhnent.com")) {
            throw new RuntimeException("exception occurred");
        }

        entityManager.merge(member);

        return 1;
    }

}
