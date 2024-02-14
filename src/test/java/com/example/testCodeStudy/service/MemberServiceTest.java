package com.example.testCodeStudy.service;

import com.example.testCodeStudy.domain.Member;
import com.example.testCodeStudy.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
class MemberServiceTest {
    // 테스트의 주체인 service
    MemberService memberService;

    // 테스트를 하기 위한 repository mock 객체
    @MockBean
    MemberRepository memberRepository;

    // memberService에 생성한 가짜 객체 주입
    @BeforeEach
    void setUp() {
        memberService = new MemberService(memberRepository);
    }

    @Test
    @DisplayName("멤버 생성 성공 테스트")
    void createMemberSuccess() {
        // given
        Member member = Member.builder().name("Juhyun").age(10).build();
        ReflectionTestUtils.setField(member,"id",1l);

        Mockito.when(memberRepository.save(member)).thenReturn(member);

        // when
        Long Juhyun = memberService.createMember("Juhyun", 10);

        // then
        assertThat(Juhyun).isEqualTo(1L);
    }

    @Test
    @DisplayName("이름 중복 예외 테스트")
    void createMemberFail() {
        // given
        Member member = Member.builder().name("Juhyun").age(10).build();

        Mockito.when(memberRepository.findByName("Juhyun")).thenReturn(Optional.of(member));

        // when, then
        assertThatThrownBy(() ->
                memberService.createMember("Juhyun", 10)).isInstanceOf(IllegalStateException.class);
    }
}