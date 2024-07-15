import { Component, OnInit } from '@angular/core';
import { StduentsService } from '../services/stduents.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-payment-details',
  templateUrl: './payment-details.component.html',
  styleUrl: './payment-details.component.css'
})
export class PaymentDetailsComponent implements OnInit{

  paymentId! : number;
  pdfFileUrl! : any;
  constructor(private studentService : StduentsService, private activatedRoute : ActivatedRoute){}
  ngOnInit(): void {
    this.paymentId = this.activatedRoute.snapshot.params['id'];
    this.studentService.getPaymentDetails(this.paymentId).subscribe({
      next : value =>{
        let blob =  new Blob([value],{type : 'application/pdf'});
        this.pdfFileUrl =  window.URL.createObjectURL(blob);
      },
      error : err =>{
        console.log(err);
      }
    })
  }

}
