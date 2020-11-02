import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'withdraw',
  templateUrl: 'withdraw.html'
})
export class Withdraw {

  constructor(public dialog: MatDialog, private httpClient: HttpClient) {}

  openDialog(): void {
    let dialogRef = this.dialog.open(WithdrawDialog, {
      width: '450px',
      data: { }
    });

    dialogRef.afterClosed().subscribe(result => {
      
      this.withdraw(result.accountNumber, result.password, result.amount);
    });
  }

  withdraw( accountNumber: string, password: string, amount: string ): any { 

    let header: HttpHeaders = new HttpHeaders();
    header = header.set('Content-Type', 'application/json');
    header = header.append('Accept', 'application/json');

    return this.httpClient.request(
        'delete',
        '/api/account',
        { 
          headers: header,
          body: {"accountNumber":accountNumber, "password": password, "amount":amount}
        }
    ).subscribe(
        (res: any) => {
            alert(`Olá '${res.name}', seu novo saldo é R$${res.balance}.`);
        },
        (err: any) => {
            if( err.status == 404){
              alert(`Não foi possível encontrar a sua conta.`);
            }
            else if(err.status == 500){
              alert(err.error.errorMessage);
            }
            else{
              alert(`Erro ao tentar efetuar o saque.`);
            }
        }
    );
  }
}

@Component({
  selector: 'withdraw-dialog',
  templateUrl: 'withdraw-dialog.html',
})
export class WithdrawDialog {

  constructor(
    public dialogRef: MatDialogRef<WithdrawDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  onNoClick(): void {
    this.dialogRef.close();
  }
}