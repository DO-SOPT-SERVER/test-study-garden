package com.example.seminar.controller;

import com.example.seminar.domain.Post;
import com.example.seminar.dto.request.post.PostCreateRequest;
import com.example.seminar.dto.request.post.PostUpdateRequest;
import com.example.seminar.repository.PostJpaRepository;
import com.example.seminar.service.post.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = PostController.class)
@ActiveProfiles("test")
class PostControllerTest extends ControllerTestManager {
    private static final String POST_API_ENDPOINT = "/api/posts";
    private static final String CUSTOM_USER_ID = "X-Auth-Id";

    @MockBean
    PostService postService;

    @Test
    @DisplayName("게시글을 등록한다.")
    void createPost() throws Exception {
      // given
        when(postService.create(any(PostCreateRequest.class), any(Long.class)))
                .thenReturn("1");
        PostCreateRequest request = new PostCreateRequest("제목", "내용입니다.");


      // when, then
        mockMvc.perform(
                        MockMvcRequestBuilders.post(POST_API_ENDPOINT)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(json)
                                .header(CUSTOM_USER_ID, 1L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/api/posts/1"));


    }

    @Test
    @DisplayName("게시글을 수정한다.")
    void updatePost() throws Exception {
      // given
        PostUpdateRequest request = new PostUpdateRequest("바꿀래");

      // when, then
        mockMvc.perform(MockMvcRequestBuilders.patch(POST_API_ENDPOINT+"/1")
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("게시글을 삭제한다.")
    void deletePost() throws Exception {
        // given, when, then
        mockMvc.perform(MockMvcRequestBuilders.delete(POST_API_ENDPOINT+"/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}