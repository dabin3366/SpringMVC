package com.itwillbs.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.domain.MemberVO;

@Controller
public class SampleController2 {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController2.class);
	
	
	// 사용불가 리턴 : int, double
	
	// 메서드의 리턴타입이 String 일때, 
	// 리턴되는 문자열.jsp 페이지로 이동
	
	// http://localhost:8080/web/doC
	// @RequestMapping(value = "/doC")
	// @RequestMapping(value = "/doC",method = RequestMethod.GET)
	@GetMapping(value = "/doC")
	public String doC() {
		logger.info("doC() 호출!");
		return "itwill";
	}
	
	// http://localhost:8080/web/doC1
	// http://localhost:8080/web/doC1?name=itwill
	
	// @RequestParam("name") String name
	// => "name" 이름의 파라메터 정보를 name(String) 변수에 저장
	// request.getparamater("name")동일한 의미
	// =>> key-value 1:1 관계
	
	@GetMapping(value = "/doC1")
	public String doC1(@RequestParam("name") String name) {
		logger.info("doC1() 호출!");
		// request.getparamater() (x)
		logger.info("name : "+name);
		
		return "itwill";
	}
	
	// @ModelAttribute("name") String name
	// => key-value 1:N관계
	//           bean/collection
	// =>> @RequestParam동작 수행 => Model 객체에 저장 => 사용
	
	// http://localhost:8080/web/doC2?name=itwill&age=20
	// http://localhost:8080/web/doC2?userid=itwill&userpw=1234
	
	
	@GetMapping(value = "/doC2")
	// public String doC2(@ModelAttribute("name") String name) {
	// public String doC2(String name,int age) {
	public String doC2(MemberVO vo) {
		logger.info("doC2() 호출!");
//		logger.info("name : "+name);
		logger.info(vo+"");
		// model.addAttribute("memberVO",vo);
		
		return "itwill";
	}
	
	
	
	
	
	
	
	
	
}
