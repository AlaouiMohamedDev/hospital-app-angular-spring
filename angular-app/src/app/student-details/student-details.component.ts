import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StduentsService } from '../services/stduents.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Payment } from '../model/payment.model';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrl: './student-details.component.css'
})
export class StudentDetailsComponent implements OnInit{

  studentCode! : string;
  payments! : Array<Payment>;
  public dataSource : any;
  public displayedColumns = [
    "id" , "date" , "amount" , "type" , "status" ,'firstName', 'details'
  ];
  
  constructor(private studentService : StduentsService,private activatedRoute : ActivatedRoute, private router : Router){

  }
  ngOnInit(): void {
    this.studentCode = this.activatedRoute.snapshot.params['code'];
    this.studentService.getStudentPayments(this.studentCode)
      .subscribe({
        next : data =>{
          this.payments = data;
          this.dataSource = new MatTableDataSource(this.payments);
        },
        error :err =>{
          console.log(err);
        }
      })
  }

  newPayment(){
    this.router.navigateByUrl(`/admin/new-payment/${this.studentCode}`);
  }

  paymentDetails(payment : Payment){
    this.router.navigateByUrl(`/admin/payment-details/${payment.id}`);
  }

}
