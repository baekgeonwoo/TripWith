package com.janggoback.tripwith.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janggoback.tripwith.member.domain.Gender;
import com.janggoback.tripwith.member.domain.Role;
import com.janggoback.tripwith.member.repository.MemberRepository;
import com.janggoback.tripwith.member.request.MemberRegisterRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest
{
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void clean() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입")
    void test1() throws Exception {
        MemberRegisterRequest request = MemberRegisterRequest.builder()
                .email("ssafy@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/members/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        );
        Assertions.assertEquals(1L, memberRepository.count());
    }
}