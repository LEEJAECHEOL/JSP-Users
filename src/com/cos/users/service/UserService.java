package com.cos.users.service;

import java.util.List;

import com.cos.users.domain.user.User;
import com.cos.users.domain.user.UserDao;
import com.cos.users.domain.user.dto.JoinReqDto;
import com.cos.users.domain.user.dto.LoginReqDto;

public class UserService {
	private UserDao userDao;
	
	public UserService() {
		this.userDao = new UserDao();
	}
	public int 회원가입(JoinReqDto dto) {
		return userDao.save(dto);
	}
	public User 로그인(LoginReqDto dto) {
		return userDao.findByByUsernameAndPassword(dto);
	}
	public List<User> 유저리스트가져오기(){
		return userDao.selectAll();
	}
	public int 유저삭제(int id) {
		return userDao.deleteById(id);
	}
	public int 유저네임중복체크(String username) {
		return userDao.fintByUsername(username);
	}
}
