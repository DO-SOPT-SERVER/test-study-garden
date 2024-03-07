package com.example.seminar.repository;

import com.example.seminar.common.exception.MemberException;
import com.example.seminar.domain.FixtureMember;
import com.example.seminar.domain.Member;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class MemberJpaRepositoryTest {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("회원 id를 알면 회원을 찾을 수 있다.")
    void findMemberById() {
      // given
        Member member = FixtureMember.createMember("정원", "garden", 26);
        Member savedMember = memberJpaRepository.save(member);

      // when
        Member findMember = memberJpaRepository.findByIdOrThrow(savedMember.getId());

      // then
        Assertions.assertThat(findMember)
                .extracting("name", "nickname", "age")
                .containsExactly(savedMember.getName(), savedMember.getNickname(), savedMember.getAge());
    }

    @Test
    @DisplayName("존재하지 않는 회원 id를 입력할 경우 예외가 발생한다.")
    void notFindMemberById() {
        // given
        Member member = FixtureMember.createMember("정원", "garden", 26);
        Member savedMember = memberJpaRepository.save(member);

        // when, then
        Assertions.assertThatThrownBy(() -> {
            memberJpaRepository.findByIdOrThrow(0L);
        }).isInstanceOf(MemberException.class)
                .hasMessage("존재하지 않는 회원입니다.");
    }
}
