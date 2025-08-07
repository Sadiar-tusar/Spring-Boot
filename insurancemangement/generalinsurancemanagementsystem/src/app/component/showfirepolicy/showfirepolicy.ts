import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FirepolicyService } from '../../service/firepolicy.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-showfirepolicy',
  standalone: false,
  templateUrl: './showfirepolicy.html',
  styleUrl: './showfirepolicy.css'
})
export class Showfirepolicy implements OnInit{

   policies: any;

constructor(
   private policyService: FirepolicyService,   
    private router: Router,
    private cdr: ChangeDetectorRef
){}
  ngOnInit(): void {
   
   this.loadPolicy();
  }

  loadPolicy(): void{
    this.policies=this.policyService.getAllPolicies();
    this.cdr.markForCheck();
  }

   deletePolicy(id: number) {
    this.policyService.deletePolicy(id)
      .subscribe({
        next: (res) => {
          console.log(res);
           this.loadPolicy();
        this.cdr.reattach();
        },
        error: (error) => {
          console.log(error);

        }

      });
  }

   getPolicyById(id: number): void{
this.policyService.getByPolicyId(id).subscribe({

  next: () => {
        this.loadPolicy();
        this.router.navigate(['/updatepolicy',id])
      },
      error: (error) => {

      }
})
  }

}
