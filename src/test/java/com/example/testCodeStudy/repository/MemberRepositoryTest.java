package com.example.testCodeStudy.repository;

import com.example.testCodeStudy.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// JPA Repository 를 테스트 할 수 있게 하는 DataJpaTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("멤버 생성 테스트")
    void createMember() {
        // given
        Member member1 = Member.builder().name("Juhyun").age(10).build();
        Member member2 = Member.builder().name("Park").age(20).build();

        // when
        Member result1 = memberRepository.save(member1);
        Member result2 = memberRepository.save(member2);

        // then
        assertThat(result1.getAge()).isEqualTo(member1.getAge());
        assertThat(result2.getAge()).isEqualTo(member2.getAge());
    }

    @Test
    @DisplayName("멤버 리스트 반환 테스트")
    void MemberList(){
        // given
        Member member1 = Member.builder().name("hi1").age(10).build();
        Member member2 = Member.builder().name("hi2").age(20).build();
        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> result = memberRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}