package com.example.testCodeStudy.repository;

import com.example.testCodeStudy.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
}
