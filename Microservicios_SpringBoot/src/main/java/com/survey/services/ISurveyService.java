package com.survey.services;

import com.survey.response.ResumeSurveyResponseRest;
import org.springframework.http.ResponseEntity;

import com.survey.model.Survey;
import com.survey.response.SurveyResponseRest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ISurveyService {

	public ResponseEntity<SurveyResponseRest> search();

	@Transactional(readOnly = true)

	public ResponseEntity<SurveyResponseRest> searchById(Long id);
	public ResponseEntity<SurveyResponseRest> save(Survey survey);
	public ResponseEntity<SurveyResponseRest> update(Survey survey, Long id);
	public ResponseEntity<SurveyResponseRest> delete(Long id);
	// Nuevo metodo
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ResponseEntity<ResumeSurveyResponseRest> resume();
	
}
