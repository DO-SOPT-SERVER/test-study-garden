package com.example.seminar.repository;

import com.example.seminar.common.exception.MemberException;
import com.example.seminar.common.exception.PostException;
import com.example.seminar.domain.FixtureMember;
import com.example.seminar.domain.Member;
import com.example.seminar.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class PostJpaRepositoryTest {

    @Autowired
    private PostJpaRepository postJpaRepository;

    @Test
    @DisplayName("게시글 id를 알면 게시글을 찾을 수 있다.")
    void findMemberById() {
        // given
        Member member = FixtureMember.createMember("정원", "garden", 26);
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .member(member)
                .build();

        Post savedPost = postJpaRepository.save(post);

        // when
        Post findPost = postJpaRepository.findByIdOrThrow(savedPost.getId());

        // then
        Assertions.assertThat(findPost)
                .extracting("title", "content")
                .containsExactly(savedPost.getTitle(), savedPost.getContent());
    }

    @Test
    @DisplayName("존재하지 않는 회원 id를 입력할 경우 예외가 발생한다.")
    void notFindMemberById() {
        // given
        Member member = FixtureMember.createMember("정원", "garden", 26);
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .member(member)
                .build();
        Post savedPost = postJpaRepository.save(post);

        // when, then
        Assertions.assertThatThrownBy(() -> postJpaRepository.findByIdOrThrow(0L)).isInstanceOf(PostException.class)
                .hasMessage("존재하지 않는 게시글입니다.");
    }
}
