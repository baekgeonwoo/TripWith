package com.janggoback.tripwith.member.service;

import com.janggoback.tripwith.member.domain.Gender;
import com.janggoback.tripwith.member.domain.Member;
import com.janggoback.tripwith.member.domain.Role;
import com.janggoback.tripwith.member.repository.MemberRepository;
import com.janggoback.tripwith.member.request.MemberRegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class MemberServiceImplTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void clean(){
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입")
    void test1(){
        MemberRegisterRequest memberRegisterRequest = MemberRegisterRequest.builder()
                .email("ssafy@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build();
        memberService.register(memberRegisterRequest);

        assertEquals(1L, memberRepository.count());
        Member member = memberRepository.findAll().get(0);
        assertEquals("싸피", member.getName());
        assertEquals("ssafy@ssafy.com", member.getEmail());
    }

    @Test
    @DisplayName("회원탈퇴")
    void test2(){
        MemberRegisterRequest memberRegisterRequest = MemberRegisterRequest.builder()
                .email("ssafy@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build();
        memberService.register(memberRegisterRequest);
        memberService.unregister(memberRegisterRequest.getEmail());

        assertEquals(0, memberRepository.count());
    }

    @Test
    @DisplayName("관리자가 member 전부 조회 가능")
    void test3(){
        MemberRegisterRequest memberRegisterRequest1 = MemberRegisterRequest.builder()
                .email("ssafy1@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피1")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build();
        MemberRegisterRequest memberRegisterRequest2 = MemberRegisterRequest.builder()
                .email("ssafy2@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피2")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build();
        MemberRegisterRequest memberRegisterRequest3 = MemberRegisterRequest.builder()
                .email("ssafy3@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피3")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build();

        MemberRegisterRequest memberRegisterRequest4 = MemberRegisterRequest.builder()
                .email("ssafy4@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피4")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.ADMIN)
                .build();

        memberService.register(memberRegisterRequest1);
        memberService.register(memberRegisterRequest2);
        memberService.register(memberRegisterRequest3);
        memberService.register(memberRegisterRequest4);

        List<Member> memberList = memberService.getList(memberRegisterRequest4.getEmail());

    }
}