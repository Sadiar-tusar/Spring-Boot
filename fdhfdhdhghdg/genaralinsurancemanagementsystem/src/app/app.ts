import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from './service/auth.service';
import { User } from './model/user.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App implements OnInit {

  routingForm!: FormGroup;
  protected title = 'genaralinsurancemanagementsystem';
  protected titleProject = 'project';

  userRole: string | null = null;
  currentUser: User | null = null;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private cdr: ChangeDetectorRef
  ) {
    this.routingForm = this.formBuilder.group({
      routing: ['']
    });
    this.visitRouter();
  }

  ngOnInit(): void {

    // ✅ localStorage safe check
    if (typeof window !== 'undefined' && typeof localStorage !== 'undefined') {
      this.userRole = localStorage.getItem('userRole');
    }
    
    // this.userRole = localStorage.getItem('userRole');

    // তারপর Observable subscribe করো → login এর সাথে সাথে আপডেট হবে
    this.authService.userRole$.subscribe(role => {
      this.userRole = role;
      console.log('User Role updated:', role);
      this.cdr.detectChanges(); // UI force update
    });
  }

  visitRouter() {
    const route = this.routingForm.value.routing;
    if (route) {
      this.router.navigate([`/${route}`]);
    }
  }
}
