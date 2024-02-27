package com.ohgiraffers.section03.primarykey.subsection01.identity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

public class PrimaryKeyMappingTests {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
        // jpatest 라는 유닛에 맞게 factory 를 생성
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    /* 설명.
     *  strategy: 자동 생성 전략을 지정
     *   - GenerationType.IDENTITY: 기본 키 생성을 데이터베이스에 위임(MySQL 또는 MariaDB의 auto_increment)
     *   - GenerationType.SEQUENCE: 데이터베이스 시퀀스 객체 사용 (ORACLE의 SEQUENCE)
     *   - GenerationType.TABLE: 키 생성 테이블 사용
     *   - GenerationType.AUTO: 자동 선택(MySQL의 IDENTITY 또는 ORACLE 이면 SEQUENCE)
    * */

    @Test
    public void primary_key_mapping_test() {
        // given
        Member member = new Member();
//        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setNickname("홍길동");
        member.setPhone("010-1234-5678");
        member.setEmail("hong@gmail.com");
        member.setAddress("경기도 성남시 분당구 대장동");
        member.setEnrollDate(new java.util.Date());
        member.setMemberRole("ROLE_MEMBER");
        member.setStatus("Y");

        Member member2 = new Member();
//        member2.setMemberNo(1);
        member2.setMemberId("user02");
        member2.setMemberPwd("pass02");
        member2.setNickname("유관순");
        member2.setPhone("010-0301-0301");
        member2.setEmail("yoo@gmail.com");
        member2.setAddress("경기도 용인시 수지구 고기동");
        member2.setEnrollDate(new java.util.Date());
        member2.setMemberRole("ROLE_MEMBER");
        member2.setStatus("Y");

        // when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(member);
        entityManager.persist(member2);
        entityTransaction.commit();

        // then
//        Member selectedMember = entityManager.find(Member.class, 1);
//        System.out.println("selectedMember = " + selectedMember);

        String jpql = "SELECT A.memberNo FROM member_section03_subsection01 A";
        // memberNo 는 테이블의 컬럼명이 아니라 Member 의 필드명
        List<Integer> memberList = entityManager.createQuery(jpql, Integer.class).getResultList();

        memberList.forEach(System.out::println);

    }
}
