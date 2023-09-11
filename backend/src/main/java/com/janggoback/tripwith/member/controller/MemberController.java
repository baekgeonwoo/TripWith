package com.janggoback.tripwith.member.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.janggoback.tripwith.member.request.MemberRegisterRequest;
import com.janggoback.tripwith.member.response.MemberResponse;
import com.janggoback.tripwith.member.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Api
@Slf4j
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/auth/member")
	public ResponseEntity<?> getMember(@AuthenticationPrincipal UserDetails userDetails) {
		log.info("@AuthenticationPrincipal: " + userDetails);
		MemberResponse memberResponse = memberService.getMember(userDetails.getUsername());
		
		return ResponseEntity.ok(memberResponse);
	}

	@GetMapping("/auth/member/{member_id}")
	public ResponseEntity<?> getMemberByMemberId(@PathVariable("member_id") Long memberId) {
		return ResponseEntity.ok(memberService.getMemberByMemberId(memberId));
	}

	@PostMapping("/sign-up")
	public ResponseEntity<?> register(@RequestBody MemberRegisterRequest memberRegisterRequest) {
		log.info("회원가입 호출");
		memberService.register(memberRegisterRequest);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/auth/sign-out") // 프론트에서 access token제거 백에서 refresh token 삭제
	public ResponseEntity<?> signOut(@AuthenticationPrincipal UserDetails userDetails) {
		memberService.signOut(userDetails.getUsername());

		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	@DeleteMapping("/auth/{email}")
	@ApiOperation("회원 탈퇴")
	public ResponseEntity<?> unregister(@AuthenticationPrincipal UserDetails userDetails) {
		memberService.unregister(userDetails.getUsername());
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/auth")
	public ResponseEntity<?> getMemberList(@AuthenticationPrincipal UserDetails userDetails) {
		return ResponseEntity.ok(memberService.getList(userDetails.getUsername()).stream().map(MemberResponse::from).collect(Collectors.toList()));
	}
	
}
