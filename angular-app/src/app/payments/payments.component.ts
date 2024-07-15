import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { StduentsService } from '../services/stduents.service';
import { Payment } from '../model/payment.model';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrl: './payments.component.css'
})
export class PaymentsComponent implements OnInit , AfterViewInit {

  public payments! : Array<Payment>;
  public dataSource : any;
  public displayedColumns = [
    "id" , "date" , "amount" , "type" , "status" ,'firstName'
  ];
  
  @ViewChild(MatPaginator) paginator! : MatPaginator;
  @ViewChild(MatSort) sort! : MatSort;
  constructor(private studentService : StduentsService) {

  }
  ngOnInit(): void {
    this.studentService.getAllPaymnts()
      .subscribe({
        next : data =>{
          this.payments = data;
          this.dataSource = new MatTableDataSource(this.payments);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        },
        error :err =>{
          console.log(err);
        }
      })
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
}
