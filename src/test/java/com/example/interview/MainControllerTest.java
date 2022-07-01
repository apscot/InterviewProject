package com.example.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.interview.controller.MainController;
import com.example.interview.model.MainModel;
import com.example.interview.service.MainService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootTest
class MainControllerTest {

	@InjectMocks
	MainController mainController;

	@Mock
	private MainService mainService;

	@Test
	void testGetData() throws JsonMappingException, JsonProcessingException {

		MainModel mainModel = new MainModel("Nandi", "Arpan", "nandi_arpan@epam.com", "male");
		MainModel mainModel2 = new MainModel("Arpan", "Nandi", "arpan_nandi@epam.com", "male");

		List<MainModel> mainModels = new ArrayList<MainModel>();
		mainModels.add(mainModel);
		mainModels.add(mainModel2);

		when(mainService.getDataFromService()).thenReturn(mainModels);
		List<MainModel> actualResult = mainController.getData();

		assertEquals(mainModels, actualResult);

	}

}
