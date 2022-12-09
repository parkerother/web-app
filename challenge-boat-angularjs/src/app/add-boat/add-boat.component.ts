import { Component, OnInit } from '@angular/core';
import { Boat } from '../model/boat';

import { RestApiService } from '../servives/rest-api.service';

@Component({
  selector: 'app-add-boat',
  templateUrl: './add-boat.component.html',
  styleUrls: ['./add-boat.component.css']
})
export class AddBoatComponent implements OnInit {

  boat: Boat = {
    name: '',
    description: '',
  };
  submitted = false;

  constructor(private service: RestApiService) { }
  
  ngOnInit(): void {
    
  }

  saveBoat(): void {
    const data = {
      name: this.boat.name,
      description: this.boat.description
    };

    this.service.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  newBoat(): void {
    this.submitted = false;
    this.boat = {
      name: '',
      description: '', 
    };
  }


}
