package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.MemberVO;

public interface MemberDAO {
	
	// 디비서버 시간 조회()
	public String getDbTime();
	
	// 회원가입()
	public void insertMember(MemberVO vo);
	
	// 로그인()
	public MemberVO loginMember(MemberVO vo);
	public MemberVO loginMember(String id,String pw);
	
	// 회원정보수정()
	public void updateVO(MemberVO uvo);
	
	// 회원정보삭제()
	public void deleteMember(MemberVO delVO);
	
	// 회원목록 조회()
	public List<MemberVO> getMemberList();
	
	// 회원정보 조회()
	public MemberVO getMember(String id);

	
}
