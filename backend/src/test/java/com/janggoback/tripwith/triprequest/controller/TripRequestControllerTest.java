package com.janggoback.tripwith.triprequest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janggoback.tripwith.member.domain.Member;
import com.janggoback.tripwith.member.exception.MemberNotFoundException;
import com.janggoback.tripwith.member.repository.MemberRepository;
import com.janggoback.tripwith.trip.domain.Trip;
import com.janggoback.tripwith.trip.repository.TripRepository;
import com.janggoback.tripwith.triprequest.domain.Status;
import com.janggoback.tripwith.triprequest.domain.TripRequest;
import com.janggoback.tripwith.triprequest.repository.TripRequestRepository;
import com.janggoback.tripwith.triprequest.request.TripRequestCreate;
import com.janggoback.tripwith.triprequest.service.TripRequestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class TripRequestControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TripRequestRepository tripRequestRepository;

    @Autowired
    private TripRequestService tripRequestService;

    @BeforeEach
    void clean(){
        tripRepository.deleteAll();
        memberRepository.deleteAll();
        tripRequestRepository.deleteAll();
    }

//    @Test
//    @DisplayName("request 1개 조회")
//    void test1() throws Exception {
//        tripRequestRepository.save(TripRequest.builder().content("test").build());
//
//        mockMvc.perform(get("/api/trip-requests/1")
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }

//    @Test
//    @DisplayName("request 생성 성공확인")
//    void test2() throws Exception{
//
//        memberRepository.save(Member.builder().email("host@ssafy.com").build());
//        memberRepository.save(Member.builder().email("requester@ssafy.com").build());
//        Member host = memberRepository.findByEmail("host@ssafy.com").orElseThrow(MemberNotFoundException::new);
//        tripRepository.save(Trip.builder().title("동행 테스트 제목").content("동행 테스트 내용").host(host).build());
//
//        mockMvc.perform(post("/api/trip-requests/request/1")
//                        .contentType(APPLICATION_JSON)
//                        .content("내용"))
//                .andExpect(status().isOk())
//                .andDo(print());
//        assertEquals(1L,tripRequestRepository.count());
//    }

//    @Test
//    @DisplayName("request accept 성공 확인")
//    void test3() throws Exception{
//        memberRepository.save(Member.builder().email("host@ssafy.com").build());
//        memberRepository.save(Member.builder().email("requester@ssafy.com").build());
//        tripRepository.save(Trip.builder().title("동행 테스트").content("동행 테스트").host(memberRepository.findByEmail("host@ssafy.com").orElseThrow(MemberNotFoundException::new)).build());
//
//        tripRequestService.createTripRequest("requester@ssafy.com", TripRequestCreate.builder().tripId(1L).content("테스트").build());
//
//        assertEquals(1L, tripRepository.findById(1L).get().getAccepted());
//
//        mockMvc.perform(get("/api/trip-requests/1/accept")
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//
//        assertEquals(2L, tripRepository.findById(1L).get().getAccepted());
//    }

//    @Test
//    @DisplayName("request reject 성공확인")
//    void test4() throws Exception{
//        memberRepository.save(Member.builder().email("host@ssafy.com").build());
//        memberRepository.save(Member.builder().email("requester@ssafy.com").build());
//        tripRepository.save(Trip.builder().title("동행 테스트").content("동행 테스트").host(memberRepository.findByEmail("host@ssafy.com").orElseThrow(MemberNotFoundException::new)).build());
//
//        tripRequestService.createTripRequest("requester@ssafy.com", TripRequestCreate.builder().tripId(1L).content("테스트").build());
//
//        assertEquals(1L, tripRepository.findById(1L).get().getAccepted());
//
//        mockMvc.perform(get("/api/trip-requests/1/reject")
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//
//        assertEquals(Status.REJECT, tripRequestRepository.findAll().get(0).getStatus());
//    }

//    @Test
//    @DisplayName("유저가 신청한 모든 동행 신청 가져오기 성공 확인")
//    void test5() throws Exception {
//        memberRepository.save(Member.builder().email("host@ssafy.com").build());
//        memberRepository.save(Member.builder().email("requester@ssafy.com").build());
//        tripRepository.save(Trip.builder().title("동행 테스트1").content("동행 테스트1").host(memberRepository.findByEmail("host@ssafy.com").orElseThrow(MemberNotFoundException::new)).build());
//        tripRepository.save(Trip.builder().title("동행 테스트2").content("동행 테스트2").host(memberRepository.findByEmail("host@ssafy.com").orElseThrow(MemberNotFoundException::new)).build());
//
//        tripRequestService.createTripRequest("requester@ssafy.com", TripRequestCreate.builder().tripId(1L).content("테스트").build());
//        tripRequestService.createTripRequest("requester@ssafy.com", TripRequestCreate.builder().tripId(2L).content("테스트").build());
//
//        mockMvc.perform(get("/api/trip-requests/member/2")
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//
//    }

//    @Test
//    @DisplayName("각 여행의 모든 신청 확인")
//    void test5() throws Exception {
//        memberRepository.save(Member.builder().email("host@ssafy.com").build());
//        memberRepository.save(Member.builder().email("requester1@ssafy.com").build());
//        memberRepository.save(Member.builder().email("requester2@ssafy.com").build());
//        memberRepository.save(Member.builder().email("requester3@ssafy.com").build());
//        tripRepository.save(Trip.builder().title("동행 테스트").content("동행 테스트").host(memberRepository.findByEmail("host@ssafy.com").orElseThrow(MemberNotFoundException::new)).build());
//
//        tripRequestService.createTripRequest("requester1@ssafy.com", TripRequestCreate.builder().tripId(1L).content("테스트1").build());
//        tripRequestService.createTripRequest("requester2@ssafy.com", TripRequestCreate.builder().tripId(1L).content("테스트2").build());
//        tripRequestService.createTripRequest("requester3@ssafy.com", TripRequestCreate.builder().tripId(1L).content("테스트3").build());
//
//        mockMvc.perform(get("/api/trip-requests/trip/1")
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//
//    }
}