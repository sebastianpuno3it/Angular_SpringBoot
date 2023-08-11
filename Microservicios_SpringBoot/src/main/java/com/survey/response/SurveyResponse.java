package com.survey.response;

import java.util.List;

import com.survey.model.Survey;

import lombok.Data;

@Data
public class SurveyResponse {

	private List<Survey> survey;
}
