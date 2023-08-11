import { Component, Inject, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { surveyService } from 'src/app/modules/shared/services/survey.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {
      form: FormGroup;
      private surveyService= inject(surveyService);
      //public data = inject(MAT_DIALOG_DATA);
      //private dialogRef= inject(MatDialogRef);
      estadoFormulario: string = "";

      constructor(private fb: FormBuilder) {
      this.form = this.fb.group({
        email:['',Validators.required],
        option_voted:['',Validators.required]

      })
    }

    ingresar()
    {
     
    }

    onSave(){
/*
    let data = {
      email: this.form.get('email')?.value,
      option_voted: this.form.get('option_voted')?.value
    }

    if (this.data != null ){
      //update registry
      this.surveyService.updateSurvey(data, this.data.id)
              .subscribe( (data: any) =>{
                //this.dialogRef.close(1);
              }, (error:any) =>{
                //this.dialogRef.close(2);
              })
    } else {
      //create new registry
      this.surveyService.saveSurvey(data)
          .subscribe( (data : any) => {
            console.log(data);
            //this.dialogRef.close(1);
          }, (error: any) => {
            //this.dialogRef.close(2);
          })
    }*/
  }

  onCancel(){
    //this.dialogRef.close(3);
  }

  updateForm(data: any){
    this.form = this.fb.group( {
      email: [data.email, Validators.required],
      option_voted: [data.option_voted, Validators.required]
    });

  }


}