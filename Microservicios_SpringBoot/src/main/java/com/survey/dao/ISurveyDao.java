package com.survey.dao;

import com.survey.model.Survey;
import org.springframework.data.repository.CrudRepository;
public interface ISurveyDao extends CrudRepository<Survey, Long> {

}
