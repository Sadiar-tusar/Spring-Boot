import { Component, OnInit } from '@angular/core';
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
export class App implements OnInit{

  routingForm!: FormGroup;
  protected title = 'genaralinsurancemanagementsystem';
  protected titleProject = 'project';

  userRole: string | null = '';
  currentUser: User | null = null;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private authService: AuthService,
  ) {
    this.routingForm = this.formBuilder.group({
      routing: ['']
    });

    this.visitRouter();

  }


  ngOnInit(): void {
    this.authService.currentUser$.subscribe(user => {
      this.currentUser = user;
      this.userRole = user?.role || null;
    });
  }

  visitRouter() {
    const route = this.routingForm.value.routing;
    if (route) {
      this.router.navigate([`/${route}`]);
    }
  }

  
}
