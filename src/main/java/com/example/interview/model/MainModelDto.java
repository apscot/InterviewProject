package com.example.interview.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MainModelDto {
	
	@JsonProperty("person_first_name")
	private String firstName;
	@JsonProperty("person_last_name")
	private String lastName;
	@JsonProperty("person_email")
	private String email;
	@JsonProperty("person_gender")
	private String gender;

}
