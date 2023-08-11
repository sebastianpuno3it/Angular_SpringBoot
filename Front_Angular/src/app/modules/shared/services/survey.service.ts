import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const base_url = "http://localhost:8080/api/v1";

@Injectable({
  providedIn: 'root'
})
export class surveyService {

  constructor(private http: HttpClient) { }

  /**
   * get all surveys
   */
  getSurveys(){

    const endpoint = `${base_url}/survey`;
    return this.http.get(endpoint);

  }

   /**
   * get resume
   */
   getResume(){

    const endpoint = `${base_url}/survey/resume`;
    return this.http.get(endpoint);

  }

  /**
   * save the survey
   */
  saveSurvey(body: any) {
    const endpoint = `${base_url}/survey`;
    return this.http.post(endpoint, body);
  }

  /**
   * update survey
   */
  updateSurvey(body: any, id: any){
    const endpoint = `${base_url}/survey/ ${id}`;
    return this.http.put(endpoint, body);
  }

  /**
   * update Survey
   */
  deleteSurvey(id: any){
    const endpoint = `${base_url}/survey/ ${id}`;
    return this.http.delete(endpoint);
  }

  /**
   * update Survey
   */
  getSurveyById(id: any){
    const endpoint = `${base_url}/survey/ ${id}`;
    return this.http.get(endpoint);
  }


  /**
   * export excel survey
   */
  exportSurvey(){
    const endpoint = `${base_url}/survey/export/excel`;
    return this.http.get(endpoint, {
      responseType: 'blob'
    });
  }
}
