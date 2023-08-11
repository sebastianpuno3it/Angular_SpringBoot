package com.survey.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.survey.dao.IResumeDao;
import com.survey.model.ResumeSurvey;
import com.survey.response.ResumeSurveyResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.survey.dao.ISurveyDao;
import com.survey.model.Survey;
import com.survey.response.SurveyResponseRest;

@Service
public class SurveyServiceImpl implements ISurveyService {
	
	@Autowired
	private ISurveyDao surveyDao;

	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<SurveyResponseRest> search() {
		
		SurveyResponseRest response = new SurveyResponseRest();
		try {
			List<Survey> survey = (List<Survey>) surveyDao.findAll();
			response.getSurveyResponse().setSurvey(survey);
			response.setMetadata(HttpStatus.OK.toString(), "Respuesta Exitosa");
			
		} catch (Exception e) {
			response.setMetadata(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<SurveyResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SurveyResponseRest>(response, HttpStatus.OK);
	}


	@Autowired
	private IResumeDao resumeDao;


	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ResumeSurveyResponseRest> resume() {

		ResumeSurveyResponseRest response = new ResumeSurveyResponseRest();
		try {
			List<ResumeSurvey> resume = (List<ResumeSurvey>) resumeDao.getResume();
			response.getResumeResponse().setResumeSurveys(resume);
			response.setMetadata(HttpStatus.OK.toString(), "Respuesta Exitosa");

		} catch (Exception e) {
			response.setMetadata(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<ResumeSurveyResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ResumeSurveyResponseRest>(response, HttpStatus.OK);
	}



	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<SurveyResponseRest> searchById(Long id) {
		
		SurveyResponseRest response = new SurveyResponseRest();
		List<Survey> list = new ArrayList<>();
		
		
		try {
	
			Optional<Survey> category = surveyDao.findById(id);
			
			if(category.isPresent()) {
				list.add(category.get());
				response.getSurveyResponse().setSurvey(list);
				response.setMetadata(HttpStatus.OK.toString(), "Votación encontrada");
			} else {
				response.setMetadata(HttpStatus.NOT_FOUND.toString(), "Votación no encontrada");
				return new ResponseEntity<SurveyResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			response.setMetadata(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<SurveyResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SurveyResponseRest>(response, HttpStatus.OK);
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ResponseEntity<SurveyResponseRest> save(Survey survey) {
		
		SurveyResponseRest response = new SurveyResponseRest();
		List<Survey> list = new ArrayList<>();
		
		
		try {
	
			Survey surveySaved = surveyDao.save(survey);
			
			if(surveySaved != null) {
				list.add(surveySaved);
				response.getSurveyResponse().setSurvey(list);
				response.setMetadata(HttpStatus.CREATED.toString(), "Votación guardada con exito");
			} else {
				response.setMetadata(HttpStatus.BAD_REQUEST.toString(), "Votación no guardada");
				return new ResponseEntity<SurveyResponseRest>(response, HttpStatus.BAD_REQUEST);
			}


			
		} catch (Exception e) {
			response.setMetadata(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al guardar votación");
			e.getStackTrace();
			return new ResponseEntity<SurveyResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SurveyResponseRest>(response, HttpStatus.OK);
	}
	
	
	@Override
	@Transactional(readOnly = false)
	public ResponseEntity<SurveyResponseRest> update(Survey category, Long id) {
		SurveyResponseRest response = new SurveyResponseRest();
		List<Survey> list = new ArrayList<>();
		
		
		try {
			Optional<Survey> categorySearch = surveyDao.findById(id);
			
			if(categorySearch.isPresent()) {
				
				categorySearch.get().setEmail(category.getEmail());
				categorySearch.get().setOption_voted(category.getOption_voted());
				
				Survey categoryToUpdate = surveyDao.save(categorySearch.get());
				
				if(categoryToUpdate != null) {
					list.add(categoryToUpdate);
					response.getSurveyResponse().setSurvey(list);
					response.setMetadata(HttpStatus.OK.toString(), "Votación actualizada con exito");
				} else {
					response.setMetadata(HttpStatus.BAD_REQUEST.toString(), "Votación no actualizada");
					return new ResponseEntity<SurveyResponseRest>(response, HttpStatus.BAD_REQUEST);
				}

			} else {
				response.setMetadata(HttpStatus.BAD_REQUEST.toString(), "Votación no actualizada");
				return new ResponseEntity<SurveyResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			response.setMetadata(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al actualizar votación");
			e.getStackTrace();
			return new ResponseEntity<SurveyResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SurveyResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	@Transactional(readOnly = false)
	public ResponseEntity<SurveyResponseRest> delete(Long id) {
		SurveyResponseRest response = new SurveyResponseRest();
		List<Survey> list = new ArrayList<>();
		
		
		try {
	
			Optional<Survey> categorySearch = surveyDao.findById(id);
			
			if(categorySearch != null) {
				surveyDao.deleteById(id);
				list.add(categorySearch.get());
				response.getSurveyResponse().setSurvey(list);
				response.setMetadata(HttpStatus.OK.toString(), "Votación eliminada con exito");
			} else {
				response.setMetadata(HttpStatus.BAD_REQUEST.toString(), "Votación no eliminada");
				return new ResponseEntity<SurveyResponseRest>(response, HttpStatus.BAD_REQUEST);
			}


			
		} catch (Exception e) {
			response.setMetadata(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al guardar votación");
			e.getStackTrace();
			return new ResponseEntity<SurveyResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SurveyResponseRest>(response, HttpStatus.OK);
	}
}
