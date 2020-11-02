import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'deposit',
  templateUrl: 'deposit.html'
})
export class Deposit {

  constructor(public dialog: MatDialog, private httpClient: HttpClient) {}

  openDialog(): void {
    let dialogRef = this.dialog.open(DepositDialog, {
      width: '450px',
      data: { }
    });

    dialogRef.afterClosed().subscribe(result => {
      
      this.deposit(result.accountNumber, result.amount);
    });
  }

  deposit( accountNumber: string, amount: string ): any { 

    let header: HttpHeaders = new HttpHeaders();
    header = header.set('Content-Type', 'application/json');
    header = header.append('Accept', 'application/json');

    return this.httpClient.put(
        '/api/account', 
        {"accountNumber":accountNumber, "amount":amount}, 
        { headers:header }
    ).subscribe(
        (res: any) => {
            alert(`A quantia R$${res.amount} foi depositada com sucesso para '${res.name}'.`);
        },
        (err: any) => {
            if( err.status == 404){
              alert(`Não foi possível encontrar a conta informada.`);
            }
            else if(err.status == 500){
              alert(err.error.errorMessage);
            }
            else{
              alert(`Erro ao tentar efetuar o depósito.`);
            }
        }
    );
  }
}

@Component({
  selector: 'deposit-dialog',
  templateUrl: 'deposit-dialog.html',
})
export class DepositDialog {

  constructor(
    public dialogRef: MatDialogRef<DepositDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  onNoClick(): void {
    this.dialogRef.close();
  }
}