package com.example.seminar.dto.request.member;


import com.example.seminar.domain.SOPT;

public record MemberCreateRequest(
        String name,
        String nickname,
        int age,
        SOPT sopt
) {

}
