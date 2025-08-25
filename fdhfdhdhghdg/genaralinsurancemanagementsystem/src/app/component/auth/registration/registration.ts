import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../service/auth.service';
import { Router } from '@angular/router';
import { User } from '../../../model/user.model';

@Component({
  selector: 'app-registration',
  standalone: false,
  templateUrl: './registration.html',
  styleUrl: './registration.css'
})
export class Registration {

  regForm!: FormGroup;
  photoFile!: File;
   message: string = '';

  constructor( private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    
     this.regForm = this.formBuilder.group({
      name: [''],
      email: [''],
      password: [''],
      photo: [''],


    })

  }

   onPhotoSelected(event: any): void {
    if (event.target.files.length > 0) {
      this.photoFile = event.target.files[0];
      console.log('Selected file:', this.photoFile);
    }
  }

   onSubmit(): void {
      
     if (!this.photoFile) {
      this.message = 'Please upload a photo.';
      return;
    }
    if (this.regForm.invalid || this.regForm.invalid) {
      this.message = 'Please fill out all required fields.';
      return;
    }

    const user = {
      name: this.regForm.value.name,
      email: this.regForm.value.email,
      phone: this.regForm.value.phone,
      password: this.regForm.value.password,
      role: 'USER' // adjust if necessary
    };

    

    this.authService.registration(user, this.photoFile).subscribe({
      next: res => {
        this.message = res.Message || 'Registration successful!';
        this.regForm.reset();
        this.photoFile = undefined!;
      },
      error: err => {
        this.message = 'Registration failed: ' + (err.error?.Message || err.message);
      }
    });
  }

}
