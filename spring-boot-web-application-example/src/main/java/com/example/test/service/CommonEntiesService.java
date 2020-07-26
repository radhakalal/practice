package com.example.test.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.model.Location;
import com.example.test.repositry.LocationRepositry;

@Service
public class CommonEntiesService {

	@Autowired
	private LocationRepositry locationRepository;

	public Iterable<Location> getAll() {
		return locationRepository.findAll();
	}

	public Location getById(int i) {
		return locationRepository.findOne(i);
	}

	public Location create(Location record) {
		return locationRepository.save(record);
	}

	public void deleteById(int id) {

		locationRepository.delete(id);
	}

}
