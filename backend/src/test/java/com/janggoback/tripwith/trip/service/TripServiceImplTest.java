package com.janggoback.tripwith.trip.service;

import com.janggoback.tripwith.attraction.domain.Attraction;
import com.janggoback.tripwith.attraction.repository.AttractionRepository;
import com.janggoback.tripwith.auth.exception.UnAuthorizationException;
import com.janggoback.tripwith.member.domain.Gender;
import com.janggoback.tripwith.member.domain.Member;
import com.janggoback.tripwith.member.domain.Role;
import com.janggoback.tripwith.member.repository.MemberRepository;
import com.janggoback.tripwith.member.request.MemberRegisterRequest;
import com.janggoback.tripwith.member.service.MemberService;
import com.janggoback.tripwith.trip.domain.Trip;
import com.janggoback.tripwith.trip.repository.TripRepository;
import com.janggoback.tripwith.trip.request.TripCreate;
import com.janggoback.tripwith.trip.request.TripEdit;
import com.janggoback.tripwith.trip.response.TripResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class TripServiceImplTest {
    @Autowired
    private TripService tripService;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AttractionRepository attractionRepository;

    @BeforeEach
    void clean(){
        attractionRepository.deleteAll();
        memberRepository.deleteAll();
        tripRepository.deleteAll();
    }

    @Test
    @DisplayName("동행 하나 생성 후 조회")
    void test1(){

        // 동행 host
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

        List<Long> attractionIds = new ArrayList<>();
        for(int i = 1 ; i < 31 ; i++) {
            attractionIds.add(Long.valueOf(i));
        }

        TripCreate tripCreate = TripCreate.builder()
                .title("동행 제목")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .content("동행 내용")
                .attractionIds(attractionIds)
                .limitGuest(1L)
                .build();
        tripService.create(tripCreate, "ssafy@ssafy.com");
        assertEquals(1L, tripRepository.count());
        TripResponse tripResponse = tripService.get(tripRepository.findAll().get(0).getId());
        assertEquals(tripResponse.getTitle(), "동행 제목");
        assertEquals(tripResponse.getContent(), "동행 내용");
        assertEquals(tripResponse.getAttractionIds().size(), 30L);
    }

    @Test
    @DisplayName("동행 생성 후 제거")
    void test2(){

        // 동행 host
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

        TripCreate tripCreate = TripCreate.builder()
                .title("동행 제목")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .content("동행 내용")
                .limitGuest(1L)
                .build();
        tripService.create(tripCreate, "ssafy@ssafy.com");
        assertEquals(1L, tripRepository.count());
        tripService.delete(tripRepository.findAll().get(0).getId(), "ssafy@ssafy.com");
        assertEquals(0L, tripRepository.count());
    }

    @Test
    @DisplayName("동행 생성 후 제거시 권한이 없으면 UnAuthorizationException 오류가 발생한다.")
    void test3(){

        // 동행 host
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

        TripCreate tripCreate = TripCreate.builder()
                .title("동행 제목")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .content("동행 내용")
                .limitGuest(1L)
                .build();
        tripService.create(tripCreate, "ssafy@ssafy.com");
        assertEquals(1L, tripRepository.count());
        assertThrows(UnAuthorizationException.class, ()->{
            tripService.delete(tripRepository.findAll().get(0).getId(), "ssafy1@ssafy.com");
        });
    }

    @Test
    @DisplayName("동행 수정")
    void test4(){

        // 동행 host
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

        TripCreate tripCreate = TripCreate.builder()
                .title("동행 제목")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .content("동행 내용")
                .limitGuest(1L)
                .build();
        tripService.create(tripCreate, "ssafy@ssafy.com");
        assertEquals(1L, tripRepository.count());
        TripResponse tripResponse = tripService.get(tripRepository.findAll().get(0).getId());
        assertEquals(tripResponse.getTitle(), "동행 제목");
        assertEquals(tripResponse.getContent(), "동행 내용");

        TripEdit tripEdit = TripEdit.builder()
                .title("동행 제목 수정")
                .content("동행 내용 수정")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .build();
        tripService.edit(tripRepository.findAll().get(0).getId(), tripEdit, "ssafy@ssafy.com");
        TripResponse tripEditResponse = tripService.get(tripRepository.findAll().get(0).getId());
        assertEquals(tripEditResponse.getTitle(), "동행 제목 수정");
        assertEquals(tripEditResponse.getContent(), "동행 내용 수정");
    }
}