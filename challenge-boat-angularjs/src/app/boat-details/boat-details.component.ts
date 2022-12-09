import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Boat } from '../model/boat';
import { RestApiService } from '../servives/rest-api.service';

@Component({
  selector: 'app-boat-details',
  templateUrl: './boat-details.component.html',
  styleUrls: ['./boat-details.component.css']
})
export class BoatDetailsComponent implements OnInit {
  @Input() viewMode = false;

  @Input() currentBoat: Boat = {
    name: '',
    description: ''

  };

  message = '';

  constructor(
    private service: RestApiService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getBoat(this.route.snapshot.params["id"]);
    }
  }

  getBoat(id: string): void {
    this.service.get(id)
      .subscribe({
        next: (data) => {
          this.currentBoat = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }



  updateBoat(): void {
    this.message = '';

    this.service.update(this.currentBoat.id, this.currentBoat)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'This boat was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  deleteBoat(): void {
    this.service.delete(this.currentBoat.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/boats']);
        },
        error: (e) => console.error(e)
      });
  }
}
