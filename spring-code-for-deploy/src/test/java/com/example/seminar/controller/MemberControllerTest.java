package com.example.seminar.controller;

import com.example.seminar.domain.FixtureMember;
import com.example.seminar.domain.Member;
import com.example.seminar.domain.Part;
import com.example.seminar.dto.request.member.MemberCreateRequest;
import com.example.seminar.dto.request.member.MemberProfileUpdateRequest;
import com.example.seminar.dto.request.post.PostCreateRequest;
import com.example.seminar.dto.request.post.PostUpdateRequest;
import com.example.seminar.dto.response.MemberGetResponse;
import com.example.seminar.service.member.MemberService;
import com.example.seminar.service.post.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = MemberController.class)
public class MemberControllerTest extends ControllerTestManager{

    private static final String MEMBER_API_ENDPOINT = "/api/member";

    @MockBean
    MemberService memberService;


    @Test
    @DisplayName("회원을 등록한다.")
    public void createMember() throws Exception {
        // given
        when(memberService.create(any(MemberCreateRequest.class)))
                .thenReturn("1");
        Member request = FixtureMember.createMember("윤정원", "garden", 26);

        // when, then
        mockMvc.perform(
                        MockMvcRequestBuilders.post(MEMBER_API_ENDPOINT)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("회원을 조회한다.")
    public void getMember() throws Exception {
        Member member = FixtureMember.createMember("윤정원", "garden", 26);

        BDDMockito.given(memberService.getMemberById(1))
                .willReturn(MemberGetResponse.of(member));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/member/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("윤정원"))
                .andExpect(MockMvcResultMatchers.jsonPath("age").value(26))
                .andExpect(MockMvcResultMatchers.jsonPath("nickname").value("garden"));
    }

//    @Test
//    @DisplayName("회원 정보를 수정한다.")
//    void updateMember() throws Exception {
//        // given
//        MemberProfileUpdateRequest request = new MemberProfileUpdateRequest((short) 34, Part.WEB);
//
//        // when, then
//        mockMvc.perform(MockMvcRequestBuilders.patch(MEMBER_API_ENDPOINT+"/1")
//                        .content(objectMapper.writeValueAsString(request)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//    }
//
//    @Test
//    @DisplayName("회원을 삭제한다.")
//    void deleteMember() throws Exception {
//        // given, when, then
//        mockMvc.perform(MockMvcRequestBuilders.delete(MEMBER_API_ENDPOINT+"/1"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }

}
