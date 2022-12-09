import { Component, OnInit } from '@angular/core';
import { Boat } from '../model/boat';
import { RestApiService } from '../servives/rest-api.service';

@Component({
  selector: 'app-boat-list',
  templateUrl: './boat-list.component.html',
  styleUrls: ['./boat-list.component.css']
})
export class BoatListComponent implements OnInit {

  boats?: Boat[];
  currentBoat: Boat = {};
  currentIndex = -1;
  name = '';

  constructor(private service: RestApiService) { }

  ngOnInit(): void {
    this.retrieveBoats();
  }

  retrieveBoats(): void {
    this.service.getAll()
      .subscribe({
        next: (data) => {
          this.boats = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(): void {
    this.retrieveBoats();
    this.currentBoat = {};
    this.currentIndex = -1;
  }

  setActiveBoat(boat: Boat, index: number): void {
    this.currentBoat = boat;
    this.currentIndex = index;
  }

  removeAllBoats(): void {
    this.service.deleteAll()
      .subscribe({
        next: (res) => {
          console.log(res);
          this.refreshList();
        },
        error: (e) => console.error(e)
      });
  }

  

}
