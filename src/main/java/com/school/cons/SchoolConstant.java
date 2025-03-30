package com.school.cons;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class SchoolConstant {

	public static final String SAVE_SUCCESS = "save-success";
	public static final String SAVE_FAILURE = "save-success";
	public static final String DELETE_SUCCESS = "delete-success";
	public static final String DELETE_FAILURE = "delete-failure";
	public static final String UPDATE_SUCCESS = "update-success";
	public static final String UPDATE_FAILURE = "update-success";
}