package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {
	private static final Logger logger = LoggerFactory.getLogger(SampleController4.class);
	
	// http://localhost:8080/web/doE
	@RequestMapping(value = "/doE")
	public String doE(RedirectAttributes rttr) {
		logger.info("doE() 호출 !");
		
		// Model 객체 처럼 view 페이지에 정보 전달
		// rttr.addFlashAttribute(attributeValue);
		rttr.addAttribute("msg", "itwill");
		// URI에 표시O(쿼리스트링 O) F5 가능(데이터 유지)
		rttr.addFlashAttribute("msg2", "busan");
		// URI에 표시X F5 불가능(데이터 유지 불가,1회성 데이터)
		
		
		// return "/doF";
		return "redirect:/doF"; // 화면이동o, 주소변경o
		// return "forward:/doF";  // 화면이동o,주소변경x
	}
	
	@RequestMapping(value = "/doF")
	public void doF(@ModelAttribute("msg") String msg,@ModelAttribute("msg2") String msg2) {
		
		logger.info("doF() 호출 !");
		logger.info("msg :"+msg);
		logger.info("msg2 :"+msg2);
	}
}
