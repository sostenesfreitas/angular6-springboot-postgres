import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { Phone } from '../phone'
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-form-phone',
  templateUrl: './form-phone.component.html',
  styleUrls: ['./form-phone.component.scss']
})
export class FormPhoneComponent implements OnInit {
  registerForm: FormGroup;
  submitted = false;
  phone: Phone = new Phone();

  colors = ['BLACK', 'WHITE', 'PINK', 'GOLD'];

  constructor(private data: DataService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      price: ['', Validators.required],
      brand: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      model: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      photo: ['', [Validators.required, Validators.maxLength(255)]],
      startDate :['', Validators.required],
      endDate :['', Validators.required],
      code: ['', Validators.required],
      color: ['', Validators.required]
    });
  }
  get f() {
    return this.registerForm.controls;
  }
 
  save() {
    this.data.createPhone(this.phone)
      .subscribe(data => console.log(data), error => console.log(error.error.text));
    this.phone = new Phone();
    
  }

  onSubmit(){
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }
 
    this.phone.price = this.f.price.value;
    this.phone.brand= this.f.brand.value;
    this.phone.code = this.f.code.value;
    this.phone.color = this.f.color.value;
    this.phone.endDate = this.f.endDate.value;
    this.phone.model = this.f.model.value;
    this.phone.photo = this.f.photo.value;
    this.phone.startDate = this.f.startDate.value;
    
    this.save();
  }
}
