package com.cos.users.domain.user;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String role;
	private Timestamp createDate;
	private Timestamp updateDate;
	
}
