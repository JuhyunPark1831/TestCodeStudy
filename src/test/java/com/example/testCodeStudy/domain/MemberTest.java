package com.example.testCodeStudy.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {
    @Test
    @DisplayName("멤버 생성 테스트")
    void createMember() {
        // given
        Member member = Member.builder().age(10).name("Juhyun").build();

        // when, then
        Assertions.assertThat(member.getAge()).isEqualTo(10);
        Assertions.assertThat(member.getName()).isEqualTo("Juhyun");
    }

    @Test
    @DisplayName("나이 변경 테스트")
    void changeAgeTest(){
        // given
        Member member = Member.builder().age(10).name("Juhyun").build();

        // when
        member.changeAge(20);

        // then
        Assertions.assertThat(member.getAge()).isEqualTo(20);
    }
}