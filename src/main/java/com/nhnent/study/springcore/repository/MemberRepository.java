package com.nhnent.study.springcore.repository;

import com.nhnent.study.springcore.vo.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends MemberRepositoryCustom, CrudRepository<Member, Integer> {
    Member findByEmailAndPassword(String email, String password);

}
