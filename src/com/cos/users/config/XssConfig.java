package com.cos.users.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.users.domain.user.dto.JoinReqDto;


public class XssConfig implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		String gubun = req.getParameter("cmd");
		String context = req.getContextPath();
		String uri = req.getRequestURI();
		String endPoint = uri.replace(context, "");
		gubun = gubun == null ? "" :gubun;
		if(gubun.equals("join") && endPoint.equals("/user")) {
//			System.out.println("XssConfig Run");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			username = username.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			password = password.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			email = email.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			JoinReqDto dto = new JoinReqDto();
			dto.setUsername(username);
			dto.setPassword(password);
			dto.setEmail(email);
			req.setAttribute("dto", dto);
		}
		chain.doFilter(req, resp);	

	}
	
}
