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
import com.school.entity.Teacher;
import com.school.prducer.Producer;
import com.school.repository.ITeacherRepository;

@Service
public class TeacherMgmtService implements ITeacherMgmtService {

	@Autowired
	private ITeacherRepository techRepo;

	@Value("${app.topic.name}")
	private String topic;
	@Autowired
	private Producer producer;

	private Map<String, String> message;

	@Autowired
	public TeacherMgmtService(AppConfig config) {
		message = config.getMessage();
	}

	@Override
	@CachePut(value = "teachers", key = "#teach.id")
	public String registerTeacher(Teacher teacher) {
		Teacher teacherEntity = techRepo.save(teacher);
		if (teacher.getId() > 0) {
			producer.sendMessage(topic, teacher.toString());
		}

		return teacher.getId() != null ? message.get(SchoolConstant.SAVE_SUCCESS) + teacherEntity.getId()
				: message.get(SchoolConstant.SAVE_FAILURE);
	}

	@Override
	@CachePut(value = "teachers", key = "#teach.id")
	public String updateTeacher(Teacher teacher) {
		Optional<Teacher> opt = techRepo.findById(teacher.getId());
		if (opt.isPresent()) {
			Teacher teacherEntity = opt.get();
			BeanUtils.copyProperties(teacher, teacherEntity);
			techRepo.save(teacher);
			return message.get(SchoolConstant.UPDATE_SUCCESS);

		}
		return message.get(SchoolConstant.UPDATE_FAILURE);

	}

	@Override
	@CacheEvict(value = "teachers", key = "#id")
	public String deleteTeacherById(Integer id) {
		Optional<Teacher> opt = techRepo.findById(id);
		if (opt.isPresent()) {
			Teacher teacher = opt.get();
			List<Student> list = teacher.getStud();
			list.forEach(ch -> {
				ch.setTeacher(null);

			});
			techRepo.deleteById(id);
			return message.get(SchoolConstant.DELETE_SUCCESS);

		}
		return message.get(SchoolConstant.DELETE_FAILURE);
	}

	@Override
	@Cacheable(value = "teachers")
	public List<Teacher> getAllTeacher() {
		return techRepo.findAll();
	}

	@Override
	@Cacheable(value = "teachers", key = "#id")
	public Teacher findTeacherById(Integer id) {
		return techRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
	}
}
