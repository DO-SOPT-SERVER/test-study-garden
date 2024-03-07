package com.example.seminar.domain;

public class FixtureMember {
    public static Member createMember(String name, String nickname, int age) {
        SOPT sopt = SOPT.builder()
                .part(Part.SERVER)
                .build();

        return Member.builder()
                .name(name)
                .nickname(nickname)
                .age(age)
                .sopt(sopt)
                .build();
    }
}
