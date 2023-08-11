import { Component, Inject, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { surveyService } from 'src/app/modules/shared/services/survey.service';

@Component({
  selector: 'app-new-survey',
  templateUrl: './new-survey.component.html',
  styleUrls: ['./new-survey.component.css']
})
export class NewSurveyComponent implements OnInit{

  public surveyForm!: FormGroup;
  private fb = inject(FormBuilder);
  private surveyService= inject(surveyService);
  private dialogRef= inject(MatDialogRef);
  public data = inject(MAT_DIALOG_DATA);
  estadoFormulario: string = "";

  ngOnInit(): void {

    console.log(this.data);
    this.estadoFormulario = "Agregar";
    
    this.surveyForm = this.fb.group({
      email: ['', Validators.required],
      option_voted: ['', Validators.required]
    })

    if (this.data != null ){
      this.updateForm(this.data);
      this.estadoFormulario = "Actualizar";
    }
  }

  onSave(){

    let data = {
      email: this.surveyForm.get('email')?.value,
      option_voted: this.surveyForm.get('option_voted')?.value
    }

    if (this.data != null ){
      //update registry
      this.surveyService.updateSurvey(data, this.data.id)
              .subscribe( (data: any) =>{
                this.dialogRef.close(1);
              }, (error:any) =>{
                this.dialogRef.close(2);
              })
    } else {
      //create new registry
      this.surveyService.saveSurvey(data)
          .subscribe( (data : any) => {
            console.log(data);
            this.dialogRef.close(1);
          }, (error: any) => {
            this.dialogRef.close(2);
          })
    }
  }

  onCancel(){
    this.dialogRef.close(3);
  }

  updateForm(data: any){
    this.surveyForm = this.fb.group( {
      email: [data.email, Validators.required],
      option_voted: [data.option_voted, Validators.required]
    });

  }


}
