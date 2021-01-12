package com.cos.users.domain;

import lombok.Data;

@Data
public class CommonRespDto<T> {
	private int statusCode;
	private T data;
}
