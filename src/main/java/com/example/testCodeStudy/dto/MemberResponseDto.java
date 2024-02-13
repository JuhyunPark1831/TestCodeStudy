package com.example.testCodeStudy.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDto {
    String name;
    int age;
}
