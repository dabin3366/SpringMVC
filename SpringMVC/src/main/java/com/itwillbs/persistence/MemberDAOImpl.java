package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

// @Repository : 스프링이 DAO 객체를 찾을때 해당 객체로 지정

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);

	// 디비연결 + Mybatis 주입
	@Autowired
	private SqlSessionFactory factory;
	
	// 디비연결 + mybatis + 자원해제
	@Autowired
	private SqlSession sqlSession;
	
	// mapper-namespace를 상수로 설정 
	private static final String NAMESPACE = "com.itwillbs.mapper.MemberMapper";
	
	@Override
	public String getDbTime() {
		// SqlSession session = factory.openSession();
		// session.selectOne(NAMESPACE + ".id");
		// String sdate = session.selectOne(NAMESPACE + ".getTime");
		
		String sdate = sqlSession.selectOne(NAMESPACE + ".getTime");
		
		System.out.println(" sdate : "+sdate);
		
		return sdate;
	}
	
	@Override
	public void insertMember(MemberVO vo) {
		// 1.2. 디비연결 -> X, SqlSession 객체 주입(디비연결완료)
		
		// 3. SQL 작성 & pstmt 객체 -> X, mapper.xml 에 설정
		
		// 4. SQL 실행
		// sqlSession.insert(statement);
		int result = sqlSession.insert(NAMESPACE + ".insertMember", vo);
		
		if(result > 0) {
			System.out.println(" DAO : 회원가입 성공! ");
		}
		
	}
	
	@Override
	public MemberVO loginMember(MemberVO vo) {
		MemberVO resultVO = sqlSession.selectOne(NAMESPACE+".loginMember", vo);
		logger.debug(resultVO+"");
		
		return resultVO;
	}
	
	@Override
	public MemberVO loginMember(String id, String pw) {
		// VO 객체 : 한번에 저장가능한 데이터 
//		MemberVO vo = new MemberVO();
//		vo.setUserid(id);
//		vo.setUserpw(pw);
		
		// Map객체 : 한번에 저장 불가능한 데이터
		Map<String, Object> paramMap
				= new HashMap<String, Object>();
		// paramMap.put("mapper에서 호출이름", 실제 저장할 데이터);
		paramMap.put("userid", id);
		paramMap.put("userpw", pw);
		
		MemberVO resultVO = sqlSession.selectOne(NAMESPACE+".loginMember", paramMap);
		logger.debug(resultVO+"");
		
		return resultVO;
	}
	
	@Override
	public void updateVO(MemberVO uvo) {
		// 디비연결 - x
		sqlSession.update(NAMESPACE+".updateMember", uvo);
		logger.debug("회원정보 수정완료!");
	}
	
	@Override
	public void deleteMember(MemberVO delVO) {
		sqlSession.delete(NAMESPACE+".deleteMember", delVO);
	}
	
	@Override
	public List<MemberVO> getMemberList() {
		List<MemberVO> memberList 
				= sqlSession.selectList(NAMESPACE+".getMemberList");
		
		return memberList;
	}
	
	@Override
	public MemberVO getMember(String id) {
		// sqlSession 객체 - 디비연결,쿼리 실행(memberMapper.xml)
		MemberVO vo = sqlSession.selectOne(NAMESPACE+".getMember",id);
		
		logger.info("getMember(id) 정보 조회 성공! ");
				
		return vo;
	}
	
	
}
