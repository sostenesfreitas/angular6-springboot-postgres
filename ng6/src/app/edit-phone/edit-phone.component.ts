import { Component, OnInit } from "@angular/core";
import { Phone } from "../phone";
import { DataService } from "../data.service";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-edit-phone",
  templateUrl: "./edit-phone.component.html",
  styleUrls: ["./edit-phone.component.scss"]
})
export class EditPhoneComponent implements OnInit {
  id: number;
  phone: any;
  t: Date;
  colors = ["BLACK", "WHITE", "GOLD", "PINK"];
  constructor(private route: ActivatedRoute, private data: DataService) {
    this.route.params.subscribe(params => (this.id = params.id));
  }

  ngOnInit() {
    this.data.getPhone(this.id).subscribe(data => this.phone = data);
  }
  onDateChange(event){
    this.phone.startDate = event
  }
  onDateEndChange(event){
    this.phone.endDate = event
  }
  onUpdate() {
    console.log(this.phone)
    this.data
      .updatePhone(this.id, this.phone)
      .subscribe(data => { 
        console.log(data) 
        this.phone = new Phone()
      });
  }
}
