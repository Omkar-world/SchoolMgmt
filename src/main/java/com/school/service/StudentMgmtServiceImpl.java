package com.school.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.school.config.AppConfig;
import com.school.cons.SchoolConstant;
import com.school.entity.Student;
import com.school.prducer.Producer;
import com.school.repository.IStudentRepsitory;

@Service
public class StudentMgmtServiceImpl implements IStudentMgmtService {

	@Autowired
	private Producer producer;
	@Autowired
	private IStudentRepsitory studentRepo;

	private Map<String, String> message;
	@Value("${app.topic.name}")
	private String topic;

	@Autowired
	public StudentMgmtServiceImpl(AppConfig config) {
		message = config.getMessage();
	}

	@Override
	@CachePut(value = "students", key = "#student.id")
	public String registerStudent(Student student) {
		Student studentEntity = studentRepo.save(student);
		if (student.getId() > 0) {
			producer.sendMessage(topic, student.toString());
		}
		return student.getId() != null ? message.get(SchoolConstant.SAVE_SUCCESS) + studentEntity.getId()
				: message.get(SchoolConstant.SAVE_FAILURE);

	}

	@Override
	@CachePut(value = "students", key = "#student.id")
	public String updateStudent(Student student) {
		Optional<Student> opt = studentRepo.findById(student.getId());
		if (opt.isPresent()) {
			Student studentEntity = opt.get();
			BeanUtils.copyProperties(student, studentEntity);
			studentRepo.save(student);
			return message.get(SchoolConstant.UPDATE_SUCCESS);

		}
		return message.get(SchoolConstant.UPDATE_FAILURE);

	}

	@Override
	@CacheEvict(value = "students", key = "#id")
	public String deleteStudentById(Integer id) {
		Optional<Student> opt = studentRepo.findById(id);
		if (opt.isPresent()) {
			Student student = opt.get();

			studentRepo.deleteById(id);
			return message.get(SchoolConstant.DELETE_SUCCESS);
		}

		return message.get(SchoolConstant.DELETE_FAILURE);
	}

	@Override
	@Cacheable(value = "students")
	public List<Student> getAllStudent() {
		return studentRepo.findAll();
	}

	@Override
	@Cacheable(value = "students", key = "#id")
	public Student findStudentById(Integer id) {
		return studentRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id"));

	}
}
