package com.example.seminar.domain;


import com.example.seminar.common.exception.MemberException;
import com.example.seminar.repository.MemberJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class MemberTest {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("사용자 정보를 입력하면 회원을 등록할 수 있다.")
    void memberSaveTest() {
        // given
        SOPT sopt = SOPT.builder()
                .part(Part.SERVER)
                .build();

        Member member = Member.builder()
                .age(26)
                .name("윤가든")
                .sopt(sopt)
                .nickname("garden")
                .build();

        // when
        Member savedMember = memberJpaRepository.save(member);

        // then
        Assertions.assertThat(savedMember)
                .extracting("name", "nickname",  "age", "sopt")
                .containsExactly(member.getName(), member.getNickname(), member.getAge(),member.getSopt());
    }

    @Test
    @DisplayName("회원 나이가 0세 미만일 경우 예외가 발생한다.")
    void memberAgeUnder0() {
      // given
        SOPT sopt = SOPT.builder()
                .part(Part.SERVER)
                .build();

      // when, then
        Assertions.assertThatThrownBy(() -> {
            Member member = Member.builder()
                    .age(-4)
                    .name("가든")
                    .sopt(sopt)
                    .nickname("garden")
                    .build();
        }).isInstanceOf(MemberException.class)
        .hasMessage("회원의 나이는 0세 이상 100세 이하입니다.");
    }

    @Test
    @DisplayName("회원 이름이 영어일 경우 예외가 발생한다.")
    void memberNameEnglish() {
        // given
        SOPT sopt = SOPT.builder()
                .part(Part.WEB)
                .build();

        // when, then
        Assertions.assertThatThrownBy(() -> {
                    Member member = Member.builder()
                            .age(23)
                            .name("garden")
                            .sopt(sopt)
                            .nickname("garden")
                            .build();
                }).isInstanceOf(MemberException.class)
                .hasMessage("유저의 이름은 한글만 가능합니다.");
    }

    @Test
    @DisplayName("회원 이름이 12자 초과일 경우 경우 예외가 발생한다.")
    void memberNameOver12() {
        // given
        SOPT sopt = SOPT.builder()
                .part(Part.IOS)
                .build();

        // when, then
        Assertions.assertThatThrownBy(() -> {
                    Member member = Member.builder()
                            .age(23)
                            .name("가나다라마바사아자차카타파하하하하")
                            .sopt(sopt)
                            .nickname("garden")
                            .build();
                }).isInstanceOf(MemberException.class)
                .hasMessage("유저의 이름은 12자를 넘길 수 없습니다.");
    }

    @Test
    @DisplayName("회원 닉네임이 8자 초과일 경우 경우 예외가 발생한다.")
    void memberNicknameOver8() {
        // given
        SOPT sopt = SOPT.builder()
                .part(Part.ANDROID)
                .build();

        // when, then
        Assertions.assertThatThrownBy(() -> {
                    Member member = Member.builder()
                            .age(23)
                            .name("가나다")
                            .sopt(sopt)
                            .nickname("abcccccccde")
                            .build();
                }).isInstanceOf(MemberException.class)
                .hasMessage("유저의 닉네임은 8자를 넘길 수 없습니다.");
    }
}
