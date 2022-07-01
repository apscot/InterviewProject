package com.example.interview.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.interview.model.MainModel;
import com.example.interview.model.MainModelDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MainService {

	@Autowired
	RestTemplate restTemplate;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<MainModel> getDataFromService() throws JsonMappingException, JsonProcessingException {

		String url = "https://w349z3oss6.execute-api.eu-west-2.amazonaws.com/prod/data-1";
		List<MainModel> mainModels = firstApiCall(url);
		// second one

		url = "https://w349z3oss6.execute-api.eu-west-2.amazonaws.com/prod/data-2";
		List<MainModelDto> mainModels2 = secondApiCall(url);

		fitDTOtoModel(mainModels, mainModels2);

		logger.info("To check the final size of the model:" + mainModels.size());
		return mainModels;
	}

	private void fitDTOtoModel(List<MainModel> mainModels, List<MainModelDto> mainModels2) {
		for (MainModelDto mainModeldto : mainModels2) {

			MainModel mainModel = new MainModel();
			mainModel.setFirstName(mainModeldto.getFirstName());
			mainModel.setLastName(mainModeldto.getLastName());
			mainModel.setEmail(mainModeldto.getEmail());
			mainModel.setGender(mainModeldto.getGender());
			mainModels.add(mainModel);
		}
	}

	private List<MainModelDto> secondApiCall(String url) throws JsonProcessingException, JsonMappingException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> jsonEntity = null;

		do {

			jsonEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		} while (jsonEntity.getBody().toString().equalsIgnoreCase("{\"message\":\"An error occured\"}"));

		List<MainModelDto> mainModels2 = new ArrayList<MainModelDto>();

		ObjectMapper mapper = new ObjectMapper();
		mainModels2.addAll(Arrays.asList(mapper.readValue(jsonEntity.getBody(), MainModelDto[].class)));
		return mainModels2;
	}

	private List<MainModel> firstApiCall(String url) throws JsonProcessingException, JsonMappingException {
		boolean check;

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> jsonEntity = null;

		do {
			try {
				check = false;
				jsonEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			} catch (Exception e) {
				check = true;
			}

		} while (check);

		List<MainModel> mainModels = new ArrayList<MainModel>();

		ObjectMapper mapper = new ObjectMapper();
		mainModels.addAll(Arrays.asList(mapper.readValue(jsonEntity.getBody(), MainModel[].class)));
		return mainModels;
	}
}
