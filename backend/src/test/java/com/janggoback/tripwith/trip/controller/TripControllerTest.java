package com.janggoback.tripwith.trip.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janggoback.tripwith.member.domain.Member;
import com.janggoback.tripwith.member.repository.MemberRepository;
import com.janggoback.tripwith.trip.domain.Trip;
import com.janggoback.tripwith.trip.repository.TripRepository;
import com.janggoback.tripwith.trip.request.TripCreate;
import com.janggoback.tripwith.trip.request.TripEdit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TripControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void clean(){
        tripRepository.deleteAll();
    }

    @Test
    @DisplayName("동행 목록 조회")
    void test1() throws Exception {
        Member member = Member.builder().email("sds@sds").build();
        memberRepository.save(member);
        List<Trip> requestTrips = IntStream.range(1, 31)
                        .mapToObj(i -> Trip.builder()
                                .title("동행 제목 "+i)
                                .attractionIds(new ArrayList<>())
                                .startDate(LocalDateTime.now())
                                .endDate(LocalDateTime.now())
                                .content("동행 내용 "+i)
                                .host(member)
                                .limitGuest(5L)
                                .build())
                                .collect(Collectors.toList());
        tripRepository.saveAll(requestTrips);




        mockMvc.perform(get("/api/trips")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

//    @Test
//    @DisplayName("동행 생성 Authentication 제거한 상태에서 테스트 돌림 성공 확인함")
//    void test2() throws Exception {
//        tripCreate request = tripCreate.builder()
//                .title("제목입니다.")
//                .content("내용입니다.")
//                .build();
//
//        String json = objectMapper.writeValueAsString(request);
//        memberRepository.save(Member.builder()
//                .email("ssafy@ssafy.com")
//                .build());
//
//        // expected
//        mockMvc.perform(post("/api/trips")
//                        .contentType(APPLICATION_JSON)
//                        .content(json)
//                )
//                .andExpect(status().is2xxSuccessful())
//                .andDo(print());
//        assertEquals(1L, tripRepository.count());
//
//        Trip trip = tripRepository.findAll().get(0);
//        assertEquals("제목입니다.", trip.getTitle());
//        assertEquals("내용입니다.", trip.getContent());
//    }

    @Test
    @DisplayName("동행 1개 조회")
    void test3() throws Exception {
        Member member = Member.builder().email("sds@sds").build();
        memberRepository.save(member);

        List<Long> attractionIds = new ArrayList<>();
        for(int i = 1 ; i < 31 ; i++) {
            attractionIds.add(Long.valueOf(i));
        }

        Trip trip = Trip.builder()
                .title("동행 제목 ")
                .attractionIds(new ArrayList<>())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .content("동행 내용 ")
                .host(member)
                .limitGuest(5L)
                .attractionIds(attractionIds)
                .build();

        tripRepository.save(trip);

        mockMvc.perform(get("/api/trips/{trip_id}",trip.getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

//    @Test
//    @DisplayName("동행 수정 성공 확인함")
//    void test4() throws Exception {
//        Member host = Member.builder().email("ssafy@ssafy.com").build();
//        memberRepository.save(host);
//        Trip trip2 = Trip.builder().title("제목").content("내용").host(host).build();
//        tripRepository.save(trip2);
//        assertEquals(1L, tripRepository.count());
//
//        tripEdit tripEdit = tripEdit.builder()
//                .title("제목수정")
//                .content("내용수정")
//                .build();
//
//        String json = objectMapper.writeValueAsString(tripEdit);
//        mockMvc.perform(put("/api/trips/1")
//                        .contentType(APPLICATION_JSON)
//                        .content(json)
//                )
//                .andExpect(status().is2xxSuccessful())
//                .andDo(print());
//        Trip trip = tripRepository.findAll().get(0);
//        assertEquals("제목수정", trip.getTitle());
//        assertEquals("내용수정", trip.getContent());
//    }

//    @Test
//    @DisplayName("동행 삭제 성공 확인")
//    void test5() throws Exception {
//        Member host = Member.builder().email("ssafy@ssafy.com").build();
//        memberRepository.save(host);
//        Trip trip = Trip.builder().title("제목").content("내용").host(host).build();
//        tripRepository.save(trip);
//        assertEquals(1L, tripRepository.count());
//
//        mockMvc.perform(delete("/api/trips/1")
//                        .contentType(APPLICATION_JSON)
//                )
//                .andExpect(status().is2xxSuccessful())
//                .andDo(print());
//
//        assertEquals(0, tripRepository.count());
//    }
}