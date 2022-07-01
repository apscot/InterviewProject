package com.example.interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.interview.model.MainModel;
import com.example.interview.service.MainService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/")
public class MainController {
	
	@Autowired
	MainService mainService;

	@GetMapping("getData")
	public List<MainModel> getData() throws JsonMappingException, JsonProcessingException{
		
		return mainService.getDataFromService();
	}
}
