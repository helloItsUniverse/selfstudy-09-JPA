package com.ohgiraffers.section01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class A_EntityManagerLifeCycleTests {

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

    @Test
    public void check_lifeCycle_of_entityManagerFactory_and_entityManager1() {
        // 엔티티_매니저_팩토리와_엔티티_매니저_생명주기_확인1
        System.out.println("entityManagerFactory.hashCode:" + entityManagerFactory.hashCode());
        System.out.println("entityManager.hashCode:" + entityManager.hashCode());
    }

    @Test
    public void check_lifeCycle_of_entityManagerFactory_and_entityManager2() {
        // 엔티티_매니저_팩토리와_엔티티_매니저_생명주기_확인2
        System.out.println("entityManagerFactory.hashCode:" + entityManagerFactory.hashCode());
        System.out.println("entityManager.hashCode:" + entityManager.hashCode());
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }
}
