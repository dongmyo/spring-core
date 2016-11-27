package com.nhnent.study.springcore.repository;

import com.nhnent.study.springcore.vo.Member;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MemberRepositoryCustom {
    int update(Member member) throws Exception;

}
