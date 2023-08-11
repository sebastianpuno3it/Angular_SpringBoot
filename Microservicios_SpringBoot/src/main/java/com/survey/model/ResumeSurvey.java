package com.survey.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate


public class ResumeSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
    private String genero;
    private String numero;

}


