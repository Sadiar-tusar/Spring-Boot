import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { BillModel } from '../../model/bill.model';
import { PolicymodelService } from '../../service/policymodel.service';
import { BilmodelService } from '../../service/bilmodel.service';
import { Router } from '@angular/router';
import { error } from 'node:console';
import { PolicyModel } from '../../model/policy';
import { forkJoin } from 'rxjs';
import { CarModel } from '../../model/car.model';
import { CarService } from '../../service/car.service';

@Component({
  selector: 'app-bill',
  standalone: false,
  templateUrl: './bill.html',
  styleUrl: './bill.css'
})
export class Bill implements OnInit{

  policyes: PolicyModel[]=[];
  bills: BillModel[] = [];
  // cars: CarModel[]=[];

constructor(
    private policiesService: PolicymodelService,
    private billService: BilmodelService,
    private router: Router,
    private cdr :ChangeDetectorRef,
    // private carService: CarService

  ) { }
  ngOnInit(): void {
    this.loadAllData();
    //  this.policyes = this.policiesService.viewAllPolicyForBill();

    // // Subscribe to the observable to fetch bills
    // this.billService.viewAllBill().subscribe({
    //   next: (data: BillModel[]) => {
    //     this.bills = data;
    //   },
    //   error: (error) => {
    //     console.error('Error fetching bills:', error);
    //   }
    // });
    // this.lo();
  }

  //  loadAllBill(): void{
  //   this.billService.viewAllBill().subscribe({
  //     next: (res) => {
  //       this.bills = res;
  //     },
  //     error: (error) => {
  //       console.log(error);
  //     }
  //   });
    
  // }

  loadAllData():void{
    forkJoin({
    policyes: this.policiesService.viewAllPolicyForBill(),
    // cars: this.carService.viewAllCarPolicyForBill(),
    bills: this.billService.viewAllBill()
    }).subscribe({
      next:({policyes, bills})=>{
        this.bills=bills;
        this.policyes=policyes;
        // this.cars=this.cars;
        this.cdr.markForCheck();
      },
      error:(err)=>{
        console.log(err);
      }
    });
  }

   deleteBill(id: string): void {
    this.billService.deleteBill(id)
      .subscribe({
        next: () => {
          this.loadAllData();
          this.refreshBills();
          this.cdr.reattach();
          // this.router.navigate(['/viewbill']);
        },
        error: (error) => {
          console.log(error);
        }
      });
  }

  getBillByBillId(id: string): void{
this.billService.getByBillId(id).subscribe({

  next: () => {
        this.loadAllData();
        this.router.navigate(['/updatebill',id])
      },
      error: (error) => {

      }
});
  }

  private refreshBills(): void {
    this.billService.viewAllBill().subscribe({
      next: (data: BillModel[]) => {
        this.bills = data;
      },
      error: (error) => {
        console.error('Error fetching bills:', error);
      }
    });
  }

  editBill(bill: BillModel): void {
    this.router.navigate(['/updatebill', bill.id]);
  }

  navigateToAddBill() {
    this.router.navigateByUrl('/createbill');
  }

  navigateToAddReciept() {
    this.router.navigateByUrl('/createreciept');
  }

  getFireAmount(bill: BillModel): number {
    const sumInsured = bill.policies?.sumInsured || 0;
    const fireRate = bill.fire || 0;
    return sumInsured * fireRate;
  }

  getRsdAmount(bill: BillModel): number {
    const sumInsured = bill.policies?.sumInsured || 0;
    const rsdRate = bill.rsd || 0;
    return sumInsured * rsdRate;
  }

  getNetPremium(bill: BillModel): number {
    const sumInsured = bill.policies.sumInsured || 0;
    const fireRate = bill.fire || 0;
    const rsdRate = bill.rsd || 0;

    return sumInsured * (fireRate + rsdRate);
  }

  getTaxAmount(bill: BillModel): number {
    const netPremium = this.getNetPremium(bill);
    const taxRate = 0.15; // 15% fixed tax rate

    return netPremium * taxRate;
  }

  getGrossPremium(bill: BillModel): number {
    const netPremium = this.getNetPremium(bill);
    const taxAmount = this.getTaxAmount(bill);

    return netPremium + taxAmount;
  }
}
