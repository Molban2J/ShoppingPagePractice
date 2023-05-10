package com.vam.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class Admincontroller {
	private static final Logger logger = LoggerFactory.getLogger(Admincontroller.class);
	
	@GetMapping("/main")
	public void adminGET() throws Exception{
		logger.info("관리자 페이지 이동");
	}
}
