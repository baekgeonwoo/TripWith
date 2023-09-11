package com.janggoback.tripwith.member.service;

import com.janggoback.tripwith.auth.exception.UnAuthorizationException;
import com.janggoback.tripwith.member.domain.Member;
import com.janggoback.tripwith.member.domain.Role;
import com.janggoback.tripwith.member.exception.DuplicatedEmailException;
import com.janggoback.tripwith.member.exception.MemberNotFoundException;
import com.janggoback.tripwith.member.repository.MemberRepository;
import com.janggoback.tripwith.member.request.MemberRegisterRequest;
import com.janggoback.tripwith.member.response.MemberResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public void register(MemberRegisterRequest memberRegisterRequest) {
		// todo admin 롤 부여하는거 지금은 회원 가입인데 나중엔 db에서 조작하는 걸로 바꿔야함
		if(memberRepository.findByEmail(memberRegisterRequest.getEmail()).isPresent()){
			throw new DuplicatedEmailException();
		}

		Member member = Member.builder()
				.email(memberRegisterRequest.getEmail())
				.password(memberRegisterRequest.getPassword())
				.name(memberRegisterRequest.getName())
				.age(memberRegisterRequest.getAge())
				.gender(memberRegisterRequest.getGender())
				.region(memberRegisterRequest.getRegion())
				.role(memberRegisterRequest.getRole())
				.build();

		member.passwordEncode(passwordEncoder);
		memberRepository.save(member);
	}

	@Override
	public void unregister(String email) {
		// TODO: FK 제약조건 해결
		memberRepository.deleteByEmail(email);
	}

	@Override
	public void signOut(String email) {
		// 프론트에서 쿠키에 담겨있는 access token 제거 백에서 db에 저장된 refresh token 제거
		Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
		member.deleteRefreshToken();
	}

	@Override
	public List<Member> getList(String email) {
		Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
		if(member.getRole().equals(Role.ADMIN)){
			return memberRepository.findAll();
		}
		throw new UnAuthorizationException();
	}

	@Override
	public MemberResponse getMember(String email) {
		Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
		return MemberResponse.from(member);
	}

	@Override
	public MemberResponse getMemberByMemberId(Long memberId) {
		return MemberResponse.from(memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new));
	}
}
