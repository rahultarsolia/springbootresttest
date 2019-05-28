package com.syne.mockusermanagement.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AgeInfo {
	private boolean underage;
	private boolean overRetirementAge;
	private int age;

}
