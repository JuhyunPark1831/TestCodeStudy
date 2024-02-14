package com.example.testCodeStudy;

import com.example.testCodeStudy.repository.MemberRepository;
import com.example.testCodeStudy.service.MemberService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
public class TestCodeStudyIntegrationTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("멤버 만들기")
    void createMemberSuccess() {
        Long memberId = memberService.createMember("Juhyun", 10);
        assertThat(memberId).isEqualTo(1L);
    }

    @Test
    @DisplayName("이름 중복 확인")
    void createMemberFail() {
        Long memberId = memberService.createMember("Juhyun", 10);
        assertThatThrownBy(() ->
                memberService.createMember("Juhyun", 12)).isInstanceOf(IllegalStateException.class);
    }
}
