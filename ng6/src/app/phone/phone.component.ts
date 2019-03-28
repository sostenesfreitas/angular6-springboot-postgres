import { Component, OnInit } from '@angular/core';'../phone'
import { DataService } from '../data.service';

@Component({
  selector: 'app-phone',
  templateUrl: './phone.component.html',
  styleUrls: ['./phone.component.scss']
})
export class PhoneComponent implements OnInit {
  phones: Object;
  filter: String;
  constructor(private data: DataService) { }

  ngOnInit() {
    this.data.getPhones().subscribe(
      data => this.phones = data
    )
  }

  onDelete(id: number) {
    this.data.deletePhone(id).subscribe(
      data => {
        console.log(data)
        window.location.reload();
        },
        error => console.log(error));
  }

}
