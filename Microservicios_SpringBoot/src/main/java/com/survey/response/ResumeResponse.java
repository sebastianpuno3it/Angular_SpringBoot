package com.survey.response;

import java.util.List;
import com.survey.model.ResumeSurvey;
import lombok.Data;

@Data
public class ResumeResponse {

    private List<ResumeSurvey> resumeSurveys;


}
