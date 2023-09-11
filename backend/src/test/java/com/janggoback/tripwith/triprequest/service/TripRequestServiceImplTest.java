package com.janggoback.tripwith.triprequest.service;

import com.janggoback.tripwith.member.domain.Gender;
import com.janggoback.tripwith.member.domain.Member;
import com.janggoback.tripwith.member.domain.Role;
import com.janggoback.tripwith.member.exception.MemberNotFoundException;
import com.janggoback.tripwith.member.repository.MemberRepository;
import com.janggoback.tripwith.trip.domain.Trip;
import com.janggoback.tripwith.trip.repository.TripRepository;
import com.janggoback.tripwith.trip.request.TripCreate;
import com.janggoback.tripwith.triprequest.domain.Status;
import com.janggoback.tripwith.triprequest.domain.TripRequest;
import com.janggoback.tripwith.triprequest.repository.TripRequestRepository;
import com.janggoback.tripwith.triprequest.request.TripRequestCreate;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class TripRequestServiceImplTest {
    @Autowired
    private TripRequestRepository tripRequestRepository;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TripRequestService tripRequestService;

    @BeforeEach
    void clean(){
        memberRepository.deleteAll();
        tripRequestRepository.deleteAll();
        tripRepository.deleteAll();
    }

    @Test
    @DisplayName("trip request 1개 생성 후 조회")
    void test1(){

        // host
        memberRepository.save(Member.builder()
                .email("ssafy@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build());

        //requester
        memberRepository.save(Member.builder()
                .email("requester@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build());

        // 동행
        TripCreate tripCreate = TripCreate.builder()
                .title("동행")
                .attractionIds(new ArrayList<>())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .content("내용")
                .limitGuest(4L)
                .build();
        Member host = memberRepository.findByEmail("ssafy@ssafy.com").orElseThrow(MemberNotFoundException::new);
        tripRepository.save(tripCreate.toTrip(host));

        // trip request 생성
        tripRequestService.create("requester@ssafy.com", TripRequestCreate.builder()
                        .content("test")
                        .tripId(tripRepository.findAll().get(0).getId())
                .build());
        assertEquals(1L, tripRequestRepository.count());

        // trip request 조회
        TripRequest tripRequest = tripRequestRepository.findAll().get(0);

        assertEquals(tripRequest.getTrip().getTitle(), "동행");
        assertEquals(tripRequest.getContent(), "test");
        assertEquals(tripRequest.getRequester().getEmail(), "requester@ssafy.com");
    }

    @Test
    @DisplayName("requester가 신청한 모든 trip-request 조회")
    void test2(){
        // host
        memberRepository.save(Member.builder()
                .email("ssafy@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build());

        //requester
        memberRepository.save(Member.builder()
                .email("requester@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build());

        // 동행
        Member host = memberRepository.findByEmail("ssafy@ssafy.com").orElseThrow(MemberNotFoundException::new);
        List<Trip> requestTrips = IntStream.range(1, 31)
                .mapToObj(i -> Trip.builder()
                        .title("동행 제목 " + i)
                        .content("동행 내용 " + i)
                        .host(host)
                        .build())
                .collect(Collectors.toList());
        tripRepository.saveAll(requestTrips);

        // trip request 생성
        for(int i = 0 ; i < 30 ; i++){
            tripRequestService.create("requester@ssafy.com", TripRequestCreate.builder()
                    .content("test"+i)
                    .tripId(requestTrips.get(i).getId())
                    .build());
        }

        assertEquals(30L, tripRequestRepository.count());
        List<TripRequest> tripRequestsByRequesterEmail = tripRequestService.getByRequesterEmail("requester@ssafy.com");
        assertEquals(30L, tripRequestsByRequesterEmail.size());
    }

    @Test
    @DisplayName("한 동행의 모든 trip-request 조회")
    void test3(){
        // host
        memberRepository.save(Member.builder()
                .email("ssafy@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build());

        //requester1
        memberRepository.save(Member.builder()
                .email("requester1@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build());

        //requester2
        memberRepository.save(Member.builder()
                .email("requester2@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build());

        // 동행
        Member host = memberRepository.findByEmail("ssafy@ssafy.com").orElseThrow(MemberNotFoundException::new);
        List<Trip> requestTrips = IntStream.range(1, 31)
                .mapToObj(i -> Trip.builder()
                        .title("동행 제목 " + i)
                        .content("동행 내용 " + i)
                        .host(host)
                        .build())
                .collect(Collectors.toList());
        tripRepository.saveAll(requestTrips);

        // trip request 생성
        for(int i = 0 ; i < 30 ; i++){
            tripRequestService.create("requester1@ssafy.com", TripRequestCreate.builder()
                    .content("test"+i)
                    .tripId(requestTrips.get(i).getId())
                    .build());
        }

        // trip request 생성
        for(int i = 0 ; i < 30 ; i++){
            tripRequestService.create("requester2@ssafy.com", TripRequestCreate.builder()
                    .content("test"+i)
                    .tripId(requestTrips.get(i).getId())
                    .build());
        }

        assertEquals(60L, tripRequestRepository.count());
        List<TripRequest> tripRequestsByRequesterEmail = tripRequestService.getByTripId(tripRepository.findAll().get(0).getId(), "ssafy@ssafy.com");
        assertEquals(2L, tripRequestsByRequesterEmail.size());
    }

    @Test
    @DisplayName("request 삭제")
    void test4(){
        // host
        memberRepository.save(Member.builder()
                .email("ssafy@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build());

        //requester
        memberRepository.save(Member.builder()
                .email("requester@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build());

        // 동행
        Member host = memberRepository.findByEmail("ssafy@ssafy.com").orElseThrow(MemberNotFoundException::new);
        List<Trip> requestTrips = IntStream.range(1, 31)
                .mapToObj(i -> Trip.builder()
                        .title("동행 제목 " + i)
                        .content("동행 내용 " + i)
                        .host(host)
                        .build())
                .collect(Collectors.toList());
        tripRepository.saveAll(requestTrips);

        // trip request 생성
        tripRequestService.create("requester@ssafy.com", TripRequestCreate.builder()
                .content("test")
                .tripId(requestTrips.get(0).getId())
                .build());

        assertEquals(1L, tripRequestRepository.count());
        tripRequestService.delete(tripRequestRepository.findAll().get(0).getId());
        assertEquals(0, tripRequestRepository.count());
    }

    @Test
    @DisplayName("trip request accept")
    void test5(){

        // host
        memberRepository.save(Member.builder()
                .email("ssafy@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build());

        //requester
        memberRepository.save(Member.builder()
                .email("requester@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build());

        // 동행
        TripCreate tripCreate = TripCreate.builder()
                .title("동행")
                .build();
        Member host = memberRepository.findByEmail("ssafy@ssafy.com").orElseThrow(MemberNotFoundException::new);
        tripRepository.save(tripCreate.toTrip(host));

        // trip request 생성
        tripRequestService.create("requester@ssafy.com", TripRequestCreate.builder()
                .content("test")
                .tripId(tripRepository.findAll().get(0).getId())
                .build());
        assertEquals(1L, tripRequestRepository.count());


        Trip trip = tripRepository.findAll().get(0);
        TripRequest tripRequest = tripRequestRepository.findAll().get(0);

        assertEquals(tripRequest.getStatus(), Status.WAIT);
        assertEquals(trip.getAccepted(), 1L); // 기본값은 host 1명

        tripRequestService.accept(tripRequest.getId());

        trip = tripRepository.findAll().get(0);
        tripRequest = tripRequestRepository.findAll().get(0);

        assertEquals(tripRequest.getStatus(), Status.ACCEPT);
        assertEquals(trip.getAccepted(), 2L);

    }

    @Test
    @DisplayName("trip request reject")
    void test6(){

        // host
        memberRepository.save(Member.builder()
                .email("ssafy@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build());

        //requester
        memberRepository.save(Member.builder()
                .email("requester@ssafy.com")
                .password("1234") // 암호화 돼서 저장
                .name("싸피")
                .age(9)
                .gender(Gender.M)
                .region("시군구")
                .role(Role.GUEST)
                .build());

        // 동행
        TripCreate tripCreate = TripCreate.builder()
                .title("동행")
                .build();
        Member host = memberRepository.findByEmail("ssafy@ssafy.com").orElseThrow(MemberNotFoundException::new);
        tripRepository.save(tripCreate.toTrip(host));

        // trip request 생성
        tripRequestService.create("requester@ssafy.com", TripRequestCreate.builder()
                .content("test")
                .tripId(tripRepository.findAll().get(0).getId())
                .build());
        assertEquals(1L, tripRequestRepository.count());


        Trip trip = tripRepository.findAll().get(0);
        TripRequest tripRequest = tripRequestRepository.findAll().get(0);

        assertEquals(tripRequest.getStatus(), Status.WAIT);
        assertEquals(trip.getAccepted(), 1L); // 기본값은 host 1명

        tripRequestService.reject(tripRequestRepository.findAll().get(0).getId());

        trip = tripRepository.findAll().get(0);
        tripRequest = tripRequestRepository.findAll().get(0);

        assertEquals(tripRequest.getStatus(), Status.REJECT);
        assertEquals(trip.getAccepted(), 1L);
    }

}