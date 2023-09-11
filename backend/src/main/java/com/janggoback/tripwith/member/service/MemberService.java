package com.janggoback.tripwith.member.service;

import java.util.List;

import com.janggoback.tripwith.member.domain.Member;
import com.janggoback.tripwith.member.request.MemberRegisterRequest;
import com.janggoback.tripwith.member.response.MemberResponse;

public interface MemberService {
	void register(MemberRegisterRequest memberRegisterRequest);
	void unregister(String email);
	void signOut(String email);
	List<Member> getList(String email);
	MemberResponse getMember(String email);
	MemberResponse getMemberByMemberId(Long memberId);
}
