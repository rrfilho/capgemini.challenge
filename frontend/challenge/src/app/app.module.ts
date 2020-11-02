import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { 
  MatToolbarModule,
  MatCardModule,
  MatInputModule,
  MatFormFieldModule,
  MatDialogModule,
  MatButtonModule
} from '@angular/material';

import { AppComponent } from './app.component';
import { Balance, BalanceDialog } from './balance/balance.component';
import { Deposit, DepositDialog } from './deposit/deposit.component';
import { Withdraw, WithdrawDialog } from './withdraw/withdraw.component';

@NgModule({
  declarations: [
    AppComponent,
    Balance, BalanceDialog,
    Deposit, DepositDialog,
    Withdraw, WithdrawDialog 
  ],
  entryComponents: [
    Balance, BalanceDialog,
    Deposit, DepositDialog,
    Withdraw, WithdrawDialog 
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule, 
    ReactiveFormsModule,
    HttpClientModule,
    MatToolbarModule,
    MatCardModule,
    MatInputModule,
    MatFormFieldModule,
    MatDialogModule,
    MatButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
