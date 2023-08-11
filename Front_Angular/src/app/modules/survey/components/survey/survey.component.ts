import { Component, Inject, inject, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { ConfirmComponent } from 'src/app/modules/shared/components/confirm/confirm.component';
import { surveyService } from 'src/app/modules/shared/services/survey.service';
import { NewSurveyComponent } from '../new-survey/new-survey.component';

@Component({
  selector: 'app-survey',
  templateUrl: './survey.component.html',
  styleUrls: ['./survey.component.css']
})
export class SurveyComponent implements OnInit{


  private surveyService = inject(surveyService);
  private snackBar = inject(MatSnackBar);
  public dialog = inject(MatDialog);

  ngOnInit(): void {
    this.getResume();
  }

  displayedColumns: string[] = [ 'email', 'option_voted'];
  dataSource = new MatTableDataSource<surveyElement>();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  getResume(): void {

    this.surveyService.getResume()
      .subscribe( (data:any) => {

        console.log("respuesta Surveys: ", data);
        this.processResumeResponse(data);

      }, (error: any) => {
        console.log("error: ", error);
      })
  }

  processResumeResponse(resp: any){

    const datasurvey: surveyElement[] = [];

    if( resp.metadata[0].code == "200 OK") {

      let listsurvey = resp.resumeResponse.resumeSurveys;

      listsurvey.forEach((element: surveyElement) => {
        datasurvey.push(element);
      });

      this.dataSource = new MatTableDataSource<surveyElement>(datasurvey);
      this.dataSource.paginator = this.paginator;
      
    }

  }

  opensurveyDialog(){
    const dialogRef = this.dialog.open(NewSurveyComponent , {
      width: '450px'
    });

    dialogRef.afterClosed().subscribe((result:any) => {
      
      if( result == 1){
        this.openSnackBar("Votación Agregada", "Exitosa");
        this.getResume();
      } else if (result == 2) {
        this.openSnackBar("Se produjo un error al guardar categoria", "Error");
      }
    });
  }

  edit(id:number, name: string, description: string){
    const dialogRef = this.dialog.open(NewSurveyComponent , {
      width: '450px',
      data: {id: id, name: name, description: description}
    });

    dialogRef.afterClosed().subscribe((result:any) => {
      
      if( result == 1){
        this.openSnackBar("Votación Actualizada", "Exitosa");
        this.getResume();
      } else if (result == 2) {
        this.openSnackBar("Se produjo un error al actualizar votación", "Error");
      }
    });
  }

  delete(id: any){
    const dialogRef = this.dialog.open(ConfirmComponent , {
      data: {id: id, module: "survey"}
    });

    dialogRef.afterClosed().subscribe((result:any) => {
      
      if( result == 1){
        this.openSnackBar("Votación Eliminada", "Exitosa");
        this.getResume();
      } else if (result == 2) {
        this.openSnackBar("Se produjo un error al eliminar votación", "Error");
      }
    });
  }

  buscar( termino: string){

    if( termino.length === 0){
      return this.getResume();
    }

    this.surveyService.getSurveyById(termino)
            .subscribe( (resp: any) => {
              this.processResumeResponse(resp);
            })
  }

  openSnackBar(message: string, action: string) : MatSnackBarRef<SimpleSnackBar>{
    return this.snackBar.open(message, action, {
      duration: 2000
    })

  }

}

export interface surveyElement {
  description: string;
  id: number;
  name: string;
}
