package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

// @Service : 스프링에서 서비스 객체로 인식

@Service
public class MemberServiceImpl implements MemberService{

	// MemberDAO 객체 필요함(객체 주입)
	@Inject
	private MemberDAO mdao;

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Override
	public void memberJoin(MemberVO vo) {
		// 회원가입 동작 수행 (DAO 객체 - 회원가입 메서드 호출)
		// => DAO 객체 주입 - 필요한 메서드 호출
		mdao.insertMember(vo);
	}
	
	@Override
	public MemberVO memberLogin(MemberVO vo) {
		logger.info("C -> S : memberLogin(MemberVO vo) 호출 !!");
		// DAO - 로그인 체크 메서드 호출
		MemberVO loginResultVO = mdao.loginMember(vo);
		logger.info("로그인 결과 : "+loginResultVO);
		
		logger.info("S -> C : memberLogin(vo) 결과를 리턴");
		return loginResultVO;
	}
	
	@Override
	public MemberVO memberInfo(String userid) {
		// 전달정보 저장
		// DAO - 회원정보 조회
		logger.info("memberInfo() - 회원정보 조회 ");
		
		return mdao.getMember(userid);
	}
	
	@Override
	public void memberUpdate(MemberVO vo) {
		logger.info("memberUpdate(vo) - 정보수정!");
		
		mdao.updateVO(vo);
	}
	
	@Override
	public void memberDelete(MemberVO vo) {
		logger.info("memberDelete(vo) - 회원정보 삭제");
		
		mdao.deleteMember(vo);
		
	}
	
	@Override
	public List<MemberVO> memberList() {
		return mdao.getMemberList();
	}
	
}
