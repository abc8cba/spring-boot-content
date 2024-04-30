package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.StudentDao;
import com.example.demo.model.Student;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
	
	@Autowired
	private StudentDao dao;
	
	@PostMapping("/students/student")
	public Student addStudent(@RequestBody Student student) {	
		Student obj = dao.save(student);
		return dao.findById(obj.getId()).get();
	}
	
	@GetMapping("/students")
	public List<Student> getStudents() {	
		return dao.findAll();
	}
	
	@PutMapping("/students/student")
	public Student updateStudent(@RequestBody Student student) {	
		return dao.save(student);
	}
	
	@DeleteMapping("/students/student")
	public ResponseEntity<HttpStatus> deleteStudent(@RequestBody Student student) {	
		try {
			dao.delete(student);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

}
