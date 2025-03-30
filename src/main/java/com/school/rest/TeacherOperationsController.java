package com.school.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.entity.Teacher;
import com.school.service.ITeacherMgmtService;

@RestController
@RequestMapping("/teacher-api")
public class TeacherOperationsController {

	@Autowired
	private ITeacherMgmtService service;

	@PostMapping("/save")
	@PreAuthorize("hasAnyAuthority('STUDENT','TEACHER')")

	public ResponseEntity<String> saveTeacher(@RequestBody Teacher teach) {
		// user service
		String msg = service.registerTeacher(teach);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}

	@PutMapping("/modify")
	@PreAuthorize("hasAnyAuthority('STUDENT','TEACHER')")

	public ResponseEntity<String> modifiyTeacher(@RequestBody Teacher teach) {
		// user service
		String msg = service.updateTeacher(teach);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	@PreAuthorize("hasAnyAuthority('STUDENT','TEACHER')")
	public ResponseEntity<Teacher> fecthTeacherById(@PathVariable Integer id) {
		// user service
		Teacher teach = service.findTeacherById(id);
		return new ResponseEntity<Teacher>(teach, HttpStatus.OK);

	}

	@GetMapping("/all")
	@PreAuthorize("hasAnyAuthority('STUDENT','TEACHER')")
	public ResponseEntity<List<Teacher>> fecthAll() {
		// use service
		List<Teacher> list = service.getAllTeacher();
		return new ResponseEntity<List<Teacher>>(list, HttpStatus.OK);
	}

	@DeleteMapping("/remove/{id}")
	@PreAuthorize("hasAnyAuthority('STUDENT','TEACHER')")
	public ResponseEntity<String> removeTeacher(@PathVariable Integer id) {
		// use service
		String msg = service.deleteTeacherById(id);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
