package com.example.test.controller;



import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.test.model.Location;
import com.example.test.service.CommonEntiesService;

@Controller

public class CommonEntiesController {

	@Autowired
	CommonEntiesService service;

	@GetMapping("/")
	public String getDetails(HttpServletRequest req) {
		req.setAttribute("locations", service.getAll());
		req.setAttribute("mode", "getlist");
		return "index";
	}

	@GetMapping("/updateLocation")
	public String getDetails(@RequestParam int id, HttpServletRequest req) {
		req.setAttribute("location", service.getById(id));
		req.setAttribute("mode", "editlocation");
		return "index";
	}

	@PostMapping("/save")
	public String getDetails(@ModelAttribute Location loc, HttpServletRequest req ,@RequestParam("radioName") String type) {
		loc.setLocationType(type);
		service.create(loc);
		req.setAttribute("locations", service.getAll());
		req.setAttribute("mode", "getlist");
		return "index";
	}

	@GetMapping("/newRecord")
	public String create(@ModelAttribute Location loc, HttpServletRequest req) {
		req.setAttribute("mode", "new");
		return "index";
	}

	@GetMapping("/deleteById")
	public String delete(@RequestParam int id, HttpServletRequest req) {
		service.deleteById(id);
		req.setAttribute("locations", service.getAll());
		req.setAttribute("mode", "getlist");
		return "index";
	}

}
