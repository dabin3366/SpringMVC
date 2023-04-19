package com.itwillbs.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	// Service 객체 필요(DAO 접근을 위해서)
	// private MemberService service = new MemberServiceImpl(); (X)
	@Inject
	private MemberService service; // 객체 의존 주입
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// http://localhost:8080/web/insertForm
	// http://localhost:8080/web/member/insertForm
	// -> 서버의 시작주소 (프로젝트명) /web => / 으로 변경
	// http://localhost:8080/member/insertForm
	// 회원가입 - 정보 입력
	@RequestMapping("/insertForm")
	public void memberInsert() {
		logger.debug("memberInsert() 실행!");
		logger.debug("주소에 해당하는 view 페이지 연결");
	}
	
	// 회원가입 - 정보 처리
	@RequestMapping(value = "/insertPro")
	public void memberInsertPro(HttpServletRequest request,MemberVO vo) {
		logger.debug("memberInsertPro() 호출!");
		// 기존 MVC 형태
		// 한글처리 => 필터
		// request.setCharacterEncoding("UTF-8");
		// 전달정보(파라메터) 저장(회원가입 정보)
		//	String userid = request.getParameter("userid");
		// 	logger.info(userid);

		// 스프링 - 메서드 전달인자를 통해서 자동으로 파라메터 수집
		logger.info(vo + "");
		// DB에 저장 =>> 서비스 객체를 주입
		// MVC : MemberDAO 객체 생성 => 강한결합
		// 스프링 : MemberDAO 객체 주입 => 약한결합(DI)
		// Controller - 서비스 - DAO 직접연결(x)
		logger.info(service+"");
		
		service.memberJoin(vo);

		// 페이지 이동(login)
	}
	
	///////////////////////////////////////////////////////////
	// 회원가입
	
	// http://localhost:8080/member/insert
	// 회원가입 - 정보입력
	@RequestMapping(value = "/insert",method = RequestMethod.GET)
	public String insertGET() {
		logger.info("insertGET() 호출 !");
		logger.info("/insert 주소에 연결된 view페이지(./member/insert.jsp)로 이동");
		
		return "/member/insertForm";
	}
	// 회원가입 - 정보처리
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	public String insertPOST(MemberVO vo) {
		logger.info("insertPOST() 호출 !");
		// 한글처리 (web.xml 필터 설정)
		// 전달정보 - 파라메터 저장 (컨트롤러 파라메터 자동수집)
		logger.info(vo+"");
		// 정보 저장 -> Service -> DB(DAO)
		service.memberJoin(vo);
		// 페이지 이동 - 로그인페이지
		return "redirect:/member/login";
	}
	
	// http://localhost:8080/member/login
	// 로그인 - 정보입력
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET() {
		logger.info("loginGET() 호출 !");
		logger.info("/login -> login.jsp 페이지 이동");
	}
	
	// 로그인 - 정보처리
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(MemberVO vo,HttpSession session/*,@ModelAttribute("userid") String userid*/) {
		logger.info("loginPOST() 호출 !");
		// 전달정보 저장(파라메터)
		logger.info(vo+"");
		// 서비스 -> DAO -> 로그인 판단
		MemberVO loginResultVO = service.memberLogin(vo);
		
		if(loginResultVO != null) {
			// 로그인 성공
			logger.info("로그인 성공!");
			// 아이디정보를 세션에 저장
			session.setAttribute("id", loginResultVO.getUserid());
			// main 페이지 이동
			return "redirect:/member/main";
		}else {
			// 로그인 실패
			logger.info("로그인 실패!");
			// 로그인 페이지 이동
			return "redirect:/member/login";
		}
		
	}
	
	// http://localhost:8080/member/main
	// 메인페이지
	@RequestMapping(value = "/main" ,method = RequestMethod.GET)
	public void mainGET() {
		logger.info("mainGET() 호출!");
		
		logger.info("/main -> main.jsp 페이지 이동");
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logoutGET(HttpSession session) {
		// 로그아웃 => 세션정보 초기화
		logger.info("logoutGET() 호출!");
		session.invalidate();
		// 페이지 이동
		return "redirect:/member/main";
	}
	
	// 회원 정보 조회
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String infoGET(HttpSession session,Model model) {
		logger.info("infoGET() 호출 !");
		// 회원 아이디(PK) => 세션
		String id = (String)session.getAttribute("id");
		
		if(id == null) {
			return "redirect:/member/login";
		}
		// 회원정보 조회 => 서비스 => DAO
		MemberVO vo = service.memberInfo(id);
		
		// DB에서 가져온 데이터를 view페이지로 전달
		model.addAttribute("vo",vo);
		model.addAttribute(vo); //memberVO
		
		logger.info("/info -> info.jsp 페이지 이동");
		
		return "/member/info";
	}
	// 회원정보 수정GET
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateGET(HttpSession session,Model model) {
		logger.info("updateGET() 호출!");
		
		// 세션에 저장된 아이디 정보를 사용해서 기존의 유저정보를 가져오기
		String id = (String) session.getAttribute("id");
		if (id == null) {
			return "redirect:/member/login";
		}
		// 서비스 -> DAO -> mapper
		// view 페이지 전달
		model.addAttribute(service.memberInfo(id));
		
		return "/member/updateForm";
	}
	
	// 회원정보 수정POST
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePOST(MemberVO vo) {
		logger.info("updatePOST(vo) 호출!");
		// 수정할 정보를 가져오기 => 파라메터 자동수집
		// 서비스 - 회원정보수정 동작
		service.memberUpdate(vo);
		// 페이지 이동(redirect:main)
		return "redirect:/member/main";
	}
	
	// 회원정보 삭제 - GET
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	public void deleteGET() {
		logger.info("deleteGET() 호출!");
		// view 페이지로 이동
	}
	
	// 회원정보 삭제 - POST
	@RequestMapping(value = "/delete" ,method = RequestMethod.POST)
	public String deletePOST(MemberVO vo,HttpSession session) {
		logger.info("deletePOST() 호출!");
		// 삭제정보(userid,userpw)
		
		// 서비스 - 회원정보 삭제
		service.memberDelete(vo);
		session.invalidate();
		return"redirect:/member/main";
	}
	
	// 회원정보 조회
	@RequestMapping(value = "/list" ,method = RequestMethod.GET)
	public String listGET(Model model) {
		logger.info("listGET() 호출!");
		// 서비스 - 회원정보 목록 가져오기
		List memberList = service.memberList();
		// view 페이지로 전달
//		model.addAttribute("memberList", memberList);
		model.addAttribute(memberList);
		
		return "/member/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
