import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'balance',
  templateUrl: 'balance.html'
})
export class Balance {

  constructor(public dialog: MatDialog, private httpClient: HttpClient) {}

  openDialog(): void {
    let dialogRef = this.dialog.open(BalanceDialog, {
      width: '450px',
      data: { }
    });

    dialogRef.afterClosed().subscribe(result => {
      
      this.getBalance(result.accountNumber, result.password);
    });
  }

  getBalance( accountNumber: string, password: string ): any { 

    let header: HttpHeaders = new HttpHeaders();
    header = header.set('Content-Type', 'application/json');
    header = header.append('Accept', 'application/json');

    return this.httpClient.post(
        '/api/account', 
        {"accountNumber":accountNumber, "password":password}, 
        { headers:header }
    ).subscribe(
        (res: any) => {
            alert(`Olá, ${res.name}, seu saldo é de R$${res.balance}.`);
        },
        (err: any) => {
            if( err.status == 404){
              alert(`Não foi possível encontrar sua conta.`);
            }
            else{
              alert(`Erro ao tentar obter o saldo.`);
            }
        }
    );
  }
}

@Component({
  selector: 'balance-dialog',
  templateUrl: 'balance-dialog.html',
})
export class BalanceDialog {

  constructor(
    public dialogRef: MatDialogRef<BalanceDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  onNoClick(): void {
    this.dialogRef.close();
  }
}