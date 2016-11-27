package com.nhnent.study.springcore.service;

import com.nhnent.study.springcore.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service("memberService")
public class MemberServiceTx implements MemberService {
    @Autowired
    MemberServiceImpl memberService;

    @Autowired
    PlatformTransactionManager transactionManager;


    @Override
    public Member createMember(String email, String password, String name) {
        return memberService.createMember(email, password, name);
    }

    @Override
    public Member getMember(String email, String password) throws Exception {
        return memberService.getMember(email, password);
    }

    @Override
    public void exchangeMemberName(Member member1, Member member2) throws Exception {
        TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            memberService.exchangeMemberName(member1, member2);
            this.transactionManager.commit(status);
        }
        catch (RuntimeException e) {
            this.transactionManager.rollback(status);
            throw e;
        }
    }

}
