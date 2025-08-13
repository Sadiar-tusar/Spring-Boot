import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { User } from '../../../model/user.model';
import { Subscription } from 'rxjs';
import { AuthService } from '../../../service/auth.service';
import { Router } from '@angular/router';
import { UserService } from '../../../service/user.service';
import { BilmodelService } from '../../../service/bilmodel.service';
import { BillModel } from '../../../model/bill.model';
import { ReceiptModel } from '../../../model/receipt.model';
import { ReceiptService } from '../../../service/receipt.service';

@Component({
  selector: 'app-userprofile',
  standalone: false,
  templateUrl: './userprofile.html',
  styleUrl: './userprofile.css'
})
export class Userprofile implements OnInit{

   user: User | null = null;
   bill: BillModel | null=null;
   receipt: ReceiptModel | null=null;
  private subscription: Subscription = new Subscription();

  constructor(
    private authService: AuthService, // ✅ fixed spelling
    private router: Router,
    private userSer: UserService,
    private billService: BilmodelService,
    private receiptService: ReceiptService,
    private cdr: ChangeDetectorRef
  ) { }
  ngOnInit(): void {
    this.loadUserProfile();
  }

  loadUserProfile(): void{
    const sub= this.userSer.getUserProfile().subscribe({
      next:(res)=>{
        console.log(res);
        if(res){
          this.user=res;
          // this.getUserShowPolicyByBillNo(this.user.id);
          this.cdr.markForCheck();
        }
      },
      error:(err)=>{
        console.log('Error Loading User Profile', err);
      }
    });

    this.subscription.add(sub);

  }

  ngOnDestroy(): void{
    this.subscription.unsubscribe();
  }

  getUserShowPolicyByBillNo(billNo:number):void{
    this.receiptService.getReciptById(billNo).subscribe({
      next:(data)=>{
      this.receipt=data;
      this.cdr.markForCheck();
      }

    });
  }

}
