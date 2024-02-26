package com.ohgiraffers.section01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class A_EntityManagerLifeCycleTests {

    /* 필기.
     *  entity manager factory 란?
     *   1. entity manager 를 생성할 수 있는 기능을 제공하는 팩토리 클래스이다.
     *   2. thread-safe 하기 때문에 여러 thread 가 동시에 접근해도 안전함. 서로 다른 thread 간 공유해서 재사용한다.
     *   3. thread-safe 한 기능을 요청 스코프마다 생성하기에는 비용(시간, 메모리) 부담이 크므로
     *      application 스코프와 동일하게 싱글톤으로 생성해서 관리하는 것이 효율적이다.
     *   4. 따라서 DB 를 사용하는 애플리케이션 당 한 개의 EntityManagerFactory 를 생성한다.
     *   .
     *   * thread-safe: 스레드 여러개가 동시에 돌아가지 않는다.
     *
     * 필기.
     *  entity manager 란?
     *   1. entity manager 는 entity 를 저장하는 메모리 상의 DB 를 관리하는 인스턴스이다.
     *   2. entity 를 저장하고, 수정, 삭제, 조회하는 등의 entity 의 관련된 모든 일을 한다.
     *   3. entity manager 는 thread-safe 하지 않기 때문에 동시성 문제가 발생할 수 있다.
     *   4. 따라서 thread 간 공유를 하지 않고, web의 경우 일반적으로 request scope 와 일치시킨다.
     *
     * 필기.
     *  영속성 컨텍스트(PersistenceContext)란?
     *   1. entity manager 를 통해 entity 를 저장하거나 조회하면 entity manager 는 영속성 컨텍스트에 entity 를 보관하고 관리한다.
     *   2. 영속성 컨텍스트는 entity 를 key-value 방식으로 저장하는 저장소이다.
     *   3. 영속성 컨텍스트는 entity manager 를 생성할 때 같이 하나 만들어진다.
     *   4. 그리고 entity manager 를 통해서 영속성 컨텍스트에 접근할 수 있고, 또 관리할 수 있다.
    * */

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
