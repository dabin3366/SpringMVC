package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.MemberVO;

public interface MemberService {
	// 서비스의 형태를 선언
	
	// 회원가입 - memberJoin
	public void memberJoin(MemberVO vo);
	
	// 로그인 - memberLogin
	public MemberVO memberLogin(MemberVO vo);
	
	// 회원정보 조회 - memberInfo
	public MemberVO memberInfo(String userid);
	
	// 회원정보 수정 - memeberUpdate
	public void memberUpdate(MemberVO vo);
	
	// 회원정보 삭제 - memberDelete
	public void memberDelete(MemberVO vo);
	
	// 회원목록 조회 - memberList
	public List<MemberVO> memberList();
}
