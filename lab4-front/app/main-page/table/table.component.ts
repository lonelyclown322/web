import {Component, Input} from '@angular/core';
import {Hit} from "../../shared/interfaces/hit";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent {
  @Input() hits: Array<Hit>

  ngOnInit() {
  }

  addNewHit(event: any) {
    this.hits.push(
      {
        x: event.hit.x,
        y: event.hit.y,
        r: event.hit.r,
        hit: event.hit.hitted
      }
    )
  }
}
