package com.survey.controllers;

import com.survey.model.Survey;
import com.survey.response.ResumeSurveyResponseRest;
import com.survey.services.ISurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey.response.SurveyResponseRest;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class SurveyRestController {

	@Autowired
	private ISurveyService service;

	/**
	 * get all voters
	 *
	 * @return
	 */
	@GetMapping("/survey")
	public ResponseEntity<SurveyResponseRest> searchSurveys() {

		ResponseEntity<SurveyResponseRest> response = service.search();
		return response;
	}

	/*
	 * get count resume
	 *
	 * @return
	*/
	@GetMapping("/survey/resume")
	public ResponseEntity<ResumeSurveyResponseRest> resumeSurveys() {

		ResponseEntity<ResumeSurveyResponseRest> response = service.resume();
		return response;
	}

	/**
	 * get surveys by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/survey/{id}")
	public ResponseEntity<SurveyResponseRest> searchSurveysById(@PathVariable Long id) {

		ResponseEntity<SurveyResponseRest> response = service.searchById(id);
		return response;
	}

	/**
	 * save survey
	 * 
	 * @param survey
	 * @return
	 */
	@PostMapping("/survey")
	public ResponseEntity<SurveyResponseRest> saveSurveys(@RequestBody Survey survey) {

		ResponseEntity<SurveyResponseRest> response = service.save(survey);
		return response;
	}

	/**
	 * update survey
	 * 
	 * @param survey
	 * @param id
	 * @return
	 */

	@PutMapping("/survey/{id}")
	public ResponseEntity<SurveyResponseRest> updateSurveys(@RequestBody Survey survey,
															   @PathVariable Long id) {

		ResponseEntity<SurveyResponseRest> response = service.update(survey, id);
		return response;
	}
	
	/**
	 * delete survey
	 * @param id
	 * @return
	 */
	@DeleteMapping("/survey/{id}")
	public ResponseEntity<SurveyResponseRest> deleteSurveys(
			@PathVariable Long id) {

		ResponseEntity<SurveyResponseRest> response = service.delete( id);
		return response;
	}

}
