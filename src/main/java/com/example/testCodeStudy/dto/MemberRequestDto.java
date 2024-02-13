package com.example.testCodeStudy.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequestDto {
    String name;
    int age;
}