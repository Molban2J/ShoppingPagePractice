package com.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.shop.model.MemberVO;

public class AdminInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		MemberVO lvo = (MemberVO)session.getAttribute("member");
		if(lvo == null || lvo.getAdminCk() == 0) {  //로그인 상태가 아니거나 관리자 계정이 아닌경우
			response.sendRedirect("/main");		// 메인페이지로 리다이렉트
			return false;
		}
		
		return true;
	}
}
