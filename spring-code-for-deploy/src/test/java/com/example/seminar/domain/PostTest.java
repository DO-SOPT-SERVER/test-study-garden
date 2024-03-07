package com.example.seminar.domain;


import com.example.seminar.common.exception.MemberException;
import com.example.seminar.common.exception.PostException;
import com.example.seminar.repository.PostJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class PostTest {

    @Autowired
    private PostJpaRepository postJpaRepository;

    @Test
    @DisplayName("글의 정보를 입력하면 게시글을 등록할 수 있다.")
    void postSaveTest() {
      // given
        Member member = FixtureMember.createMember("윤정원", "garden", 26);
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .member(member)
                .build();

      // when
        Post savedPost = postJpaRepository.save(post);

      // then
        Assertions.assertThat(savedPost)
                .extracting("title", "content", "member")
                .containsExactly(post.getTitle(), post.getContent(), post.getMember());
    }

    @Test
    @DisplayName("게시글 제목이 5자 초과일 경우 예외가 발생한다.")
    void postTitleOver5() {
      // given
         Member member = FixtureMember.createMember("윤정원", "garden", 26);

         // when, then
        Assertions.assertThatThrownBy(() -> {
            Post post = Post.builder()
                    .title("abcdef")
                    .content("내용")
                    .member(member)
                    .build();
        }).isInstanceOf(PostException.class)
        .hasMessage("제목은 5자 이하여야 합니다.");
    }
}
