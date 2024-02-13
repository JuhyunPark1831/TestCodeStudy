package com.example.testCodeStudy.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Override
    public boolean equals(Object obj) {
        Member me = (Member) obj;
        return this.name.equals(me.name) && this.age == me.age;
    }

    public void changeAge(int age) {
        this.age = age;
    }
}
