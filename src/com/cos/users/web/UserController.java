package com.cos.users.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.users.domain.CommonRespDto;
import com.cos.users.domain.user.User;
import com.cos.users.domain.user.dto.JoinReqDto;
import com.cos.users.domain.user.dto.LoginReqDto;
import com.cos.users.domain.user.dto.UsernameCheckReqDto;
import com.cos.users.service.UserService;
import com.cos.users.util.Script;
import com.google.gson.Gson;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		UserService userService = new UserService();
		
		if(cmd.equals("loginForm")) {
			response.sendRedirect("user/loginForm.jsp");
		}else if(cmd.equals("login")) {
			LoginReqDto dto = new LoginReqDto();
			dto.setUsername(request.getParameter("username"));
			dto.setPassword(request.getParameter("password"));
//			System.out.println("LoginDto : " + dto);
			User userEntity = userService.로그인(dto);
//			System.out.println("userEntity : " + userEntity.toString());
			if(userEntity != null) {
				HttpSession session = request.getSession();
				session.setAttribute("principal", userEntity);	// 인증주체
				response.sendRedirect("index.jsp");
			}else {
				Script.back(response, "로그인 실패");
			}
		}else if(cmd.equals("joinForm")) {
			response.sendRedirect("user/joinForm.jsp");
		}else if(cmd.equals("join")) {
			JoinReqDto dto = (JoinReqDto) request.getAttribute("dto");
//			System.out.println("JoinDto : " + dto.toString());
			
			int result = userService.회원가입(dto);
			
			if(result == 1) {
				response.sendRedirect("user/loginForm.jsp");
			}else {
				Script.back(response, "회원가입 실패");
			}
		}else if(cmd.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("index.jsp");
		}else if(cmd.equals("listForm")) {
			List<User> users = userService.유저리스트가져오기();
//			System.out.println("Users Info : " + users);
			request.setAttribute("users", users);
			RequestDispatcher dis = request.getRequestDispatcher("user/listForm.jsp");
			dis.forward(request, response);
		}else if(cmd.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
//			System.out.println("delete id : " + id);
			
			HttpSession session = request.getSession();
			User principal = (User) session.getAttribute("principal");
			String role = principal.getRole();
			
//			System.out.println("유저 권한 : " + role);
			int result = userService.유저삭제(id);
			
			if(result == 1) {
				if(role.equals("ADMIN")) {
					response.sendRedirect("/users/user?cmd=listForm");
				}else {
					response.sendRedirect("/users/user?cmd=logout");
				}
			}else {
				Script.back(response, "유저삭제 실패");
			}
			
		}else if(cmd.equals("usernameCheck")) {
			BufferedReader br = request.getReader();
			String data = br.readLine();
			Gson gson = new Gson();
			UsernameCheckReqDto dto = gson.fromJson(data, UsernameCheckReqDto.class);
//			System.out.println("중복체크 dto : " + dto);
			int result = userService.유저네임중복체크(dto.getUsername());
			CommonRespDto<String> respDto = new CommonRespDto<>();
			respDto.setStatusCode(result);
			String jsonData = gson.toJson(respDto);
			PrintWriter out = response.getWriter();
			out.print(jsonData);
			out.flush();
			
		}
		
		
	}
}
