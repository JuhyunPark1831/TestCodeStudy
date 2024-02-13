package com.example.testCodeStudy.service;


import com.example.testCodeStudy.domain.Member;
import com.example.testCodeStudy.dto.MemberResponseDto;
import com.example.testCodeStudy.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberResponseDto> findAll() {
        return memberRepository.findAll().stream()
                .map(member -> MemberResponseDto.builder() // 각 회원을 MemberResponseDto로 변환
                        .age(member.getAge())
                        .name(member.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public Long createMember(String name, int age) {
        memberRepository.findByName(name).ifPresent(a -> {
            throw new IllegalStateException("이미 있는 아이디");
        });

        Member member = Member.builder()
                .age(age)
                .name(name).build();

        return memberRepository.save(member).getId();
    }
}
