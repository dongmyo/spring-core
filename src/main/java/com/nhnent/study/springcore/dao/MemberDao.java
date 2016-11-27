package com.nhnent.study.springcore.dao;

import com.nhnent.study.springcore.vo.Member;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDao {
    private static final String NAMESPACE = "memberDao.";


    @Autowired
    SqlSessionTemplate sqlSessionTemplate;


    public Member exist(String email, String password) {
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);

        return sqlSessionTemplate.selectOne(NAMESPACE + "selectMemberExist", member);
    }

    public int insert(Member member) throws Exception  {
        return sqlSessionTemplate.insert(NAMESPACE + "insertMember", member);
    }

    public List<Member> selectList() throws Exception {
        return sqlSessionTemplate.selectList(NAMESPACE + "selectMemberList");
    }

    public Member selectOne(int no) throws Exception {
        return sqlSessionTemplate.selectOne(NAMESPACE + "selectMember", no);
    }

    public int update(Member member) throws Exception {
        // TODO: exception이 발생하면?
//        if (Objects.equals(member.getEmail(), "dongmyo2@nhnent.com")) {
//            throw new RuntimeException("exception occurred");
//        }

        return sqlSessionTemplate.update(NAMESPACE + "updateMember", member);
    }

    public int delete(int no) throws Exception {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteMember", no);
    }

}
