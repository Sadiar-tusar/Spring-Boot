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
  isAdminSelected: boolean = false;
  

  constructor(private fb: FormBuilder, private authService: AuthService) {
    this.regForm = this.fb.group({
      name: [''],
      email: [''],
      password: [''],
      phone: [''],
      role: ['USER'], // default
      adminCode: ['']
    });
  }

  onRoleChange(event: any) {
    this.isAdminSelected = event.target.value === 'ADMIN';
    if (!this.isAdminSelected) {
      this.regForm.get('adminCode')?.setValue('');
    }
  }

  onPhotoSelected(event: any) {
    if (event.target.files.length > 0) {
      this.photoFile = event.target.files[0];
    }
  }

  onSubmit() {
    if (!this.photoFile) {
      alert('Please upload a photo.');
      return;
    }

    const formValue = this.regForm.value;
    const user = {
      name: formValue.name,
      email: formValue.email,
      password: formValue.password,
      phone: formValue.phone
    };

    if (formValue.role === 'ADMIN') {
      const adminCode = formValue.adminCode;
      this.authService.registerAdmin(user, this.photoFile, adminCode).subscribe({
        next: res => alert(res),
        error: err => alert('Admin registration failed: ' + err.error?.message)
      });
    } else {
      this.authService.registerUser(user, this.photoFile).subscribe({
        next: res => alert(res),
        error: err => alert('User registration failed: ' + err.error?.message)
      });
    }
  }

}
