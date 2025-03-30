package com.school.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.entity.Student;
import com.school.service.IStudentMgmtService;

@RestController
@RequestMapping("stud-api")
public class StudentOperationsController {
	@Autowired
	private IStudentMgmtService service;

	@PostMapping("/save")
	@PreAuthorize("hasAnyAuthority('STUDENT','TEACHER')")
	public ResponseEntity<String> saveStudent(@RequestBody Student stud) {
		// user service
		String msg = service.registerStudent(stud);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	@PreAuthorize("hasAnyAuthority('STUDENT','TEACHER')")
	public ResponseEntity<String> modifiyStudentData(@RequestBody Student stud) {
		// use service
		String msg = service.updateStudent(stud);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	@PreAuthorize("hasAnyAuthority('STUDENT','TEACHER')")
	public ResponseEntity<Student> fetchStudentById(@PathVariable Integer id) {
		// use service
		Student stud = service.findStudentById(id);
		return new ResponseEntity<Student>(stud, HttpStatus.OK);
	}

	@DeleteMapping("/remove/{id}")
	@PreAuthorize("hasAnyAuthority('STUDENT','TEACHER')")

	public ResponseEntity<String> removeById(@PathVariable Integer id) {
		// user service
		String msg = service.deleteStudentById(id);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@GetMapping("/all")
	@PreAuthorize("hasAnyAuthority('STUDENT','TEACHER')")

	public ResponseEntity<List<Student>> fetchAllStudents() {
		// use service
		List<Student> list = service.getAllStudent();
		return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
	}

}
