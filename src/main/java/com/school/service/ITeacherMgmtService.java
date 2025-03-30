package com.school.service;

import java.util.List;

import com.school.entity.Teacher;

public interface ITeacherMgmtService {
	public String registerTeacher(Teacher teach);

	public List<Teacher> getAllTeacher();

	public String deleteTeacherById(Integer id);

	public String updateTeacher(Teacher teach);

	public Teacher findTeacherById(Integer id);
}
