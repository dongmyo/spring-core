package com.nhnent.study.springcore.vo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MEMBERS")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MNO")
    protected Integer	no;

    @Column(name = "MNAME")
    protected String 	name;

    @Column(name = "EMAIL")
    protected String 	email;

    @Column(name = "PWD")
    protected String 	password;

    @Temporal(TemporalType.DATE)
    @Column(name = "CRE_DATE")
    protected Date		createdDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "MOD_DATE")
    protected Date		modifiedDate;


    public Integer getNo() {
        return no;
    }
    public Member setNo(Integer no) {
        this.no = no;
        return this;
    }

    public String getName() {
        return name;
    }
    public Member setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }
    public Member setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }
    public Member setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
    public Member setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }
    public Member setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    @Override
    public String toString() {
        return "Member{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
