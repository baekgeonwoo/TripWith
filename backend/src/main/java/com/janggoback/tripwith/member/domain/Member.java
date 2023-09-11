package com.janggoback.tripwith.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.janggoback.tripwith.board.domain.Board;
import com.janggoback.tripwith.board.domain.BoardReply;
import com.janggoback.tripwith.trip.domain.Trip;
import com.janggoback.tripwith.triprequest.domain.TripRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ApiModel
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ApiModelProperty(value = "username 겸 email")
	private String email;
    private String password;
    private String name;
    private Integer age;

    @ApiModelProperty(value = "M 또는 F")
    private Gender gender;
    @ApiModelProperty(value = "공공데이터 상의 지역코드")
    private String region;

    // 인증
    @Enumerated(EnumType.STRING)
    private Role role;


    @Enumerated(EnumType.STRING)
    private SocialType socialType; // KAKAO, NAVER, GOOGLE

    private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)

    private String refreshToken; // 리프레시 토큰
    // 인증 끝
    @JsonManagedReference
    @OneToMany(mappedBy = "writer", cascade = CascadeType.REMOVE)
    private List<Board> boards = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "writer", cascade = CascadeType.REMOVE)
    private List<BoardReply> boardReplies = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "host", cascade = CascadeType.REMOVE)
    private List<Trip> Trips = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "requester", cascade = CascadeType.REMOVE)  // 연관된 테이블(trip request)의 join column한 변수명이랑 맞춰야 한다.
    private List<TripRequest> tripRequests = new ArrayList<>();

    @Builder
    public Member(String email, String password, String name, Integer age, Gender gender, String region, Role role, SocialType socialType, String socialId) {
		this.email = email;
		this.password = password; // 암호화해서 저장
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.region = region;
        this.role = role;
        this.socialType = socialType;
        this.socialId = socialId;
	}

    public void authorizeUser() {
        this.role = Role.USER;
    }

    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    public void deleteRefreshToken() {
        this.refreshToken = null;
    }
}
