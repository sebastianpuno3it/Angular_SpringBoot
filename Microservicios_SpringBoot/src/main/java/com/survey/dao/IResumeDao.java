package com.survey.dao;

import com.survey.model.ResumeSurvey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface IResumeDao extends CrudRepository<ResumeSurvey, Long> {

    @Query(nativeQuery = true, value="select v.option_voted as genero,count(v.option_voted) as numero FROM db_survey.voter v group by v.option_voted")
    List<ResumeSurvey> getResume();
}
