package com.school.service;

import java.util.List;

import com.school.entity.Student;

public interface IStudentMgmtService {

	public String registerStudent(Student stud);

	public List<Student> getAllStudent();

	public String deleteStudentById(Integer id);

	public String updateStudent(Student stud);

	public Student findStudentById(Integer id);
}
