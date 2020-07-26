package com.example.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.model.Teacher;
import com.example.test.repositry.DetailsRepositry;

@Service
public class CommonEntiesService {

	@Autowired
	private DetailsRepositry detailsRepository;

	public List<Teacher> getRecort() {
		return (List<Teacher>) detailsRepository.findAll();
	}

	public Optional<Teacher> recordByID(int i) {
		return detailsRepository.findById(i);
	}

	public Teacher saveRecords(Teacher record) {
		return detailsRepository.save(record);
	}

	public void deleteId(Teacher value) {

		detailsRepository.delete(value);
	}

}
