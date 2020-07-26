package com.example.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.test.exception.RecordNotFoundException;
import com.example.test.model.Student;
import com.example.test.model.Teacher;
import com.example.test.service.CommonEntiesService;

@RestController
@RequestMapping("/common")
public class CommonEntiesController {

	@Autowired
	CommonEntiesService service;

	@GetMapping("/allrecord")
	public List<Teacher> getDetails() {
		return service.getRecort();
	}

	@GetMapping("/allrecord/{id}")
	public ResponseEntity<Teacher> getRecordById(@PathVariable(value = "id") int teacherId)
			throws RecordNotFoundException {
		Teacher user = service.recordByID(teacherId)
				.orElseThrow(() -> new RecordNotFoundException("Recodrd not found :: " + teacherId));
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/allrecord")
	public Teacher commonuser(@RequestBody Teacher record) {
		return service.saveRecords(record);
	}

	@PutMapping("/allrecord/{id}")
	public ResponseEntity<Teacher> updateUser(@PathVariable(value = "id") int teacherid,
			@RequestBody Teacher userDetails) throws RecordNotFoundException {
		Teacher user = service.recordByID(teacherid)
				.orElseThrow(() -> new RecordNotFoundException("Record not found :: " + teacherid));
		user.setEmail(userDetails.getEmail());
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		
		Student stud=userDetails.getStudentDetail();
		
		user.setStudentDetail(stud);
		
		
		final Teacher updatedUser = service.saveRecords(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/allrecord/{id}")
	public ResponseEntity<Teacher> deleteUser(@PathVariable(value = "id") int teacherid)
			throws RecordNotFoundException {
		Teacher value = service.recordByID(teacherid)
				.orElseThrow(() -> new RecordNotFoundException("Record not found :: " + teacherid));
		service.deleteId(value);
		return ResponseEntity.ok().body(value);
	}

}
