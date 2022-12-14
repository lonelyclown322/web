import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {HttpService} from "../../shared/services/http-services.service";
import {Hit} from "../../shared/interfaces/hit";

@Component({
  selector: 'app-hit-form',
  templateUrl: './hit-form.component.html',
  styleUrls: ['./hit-form.component.css']
})
export class HitFormComponent implements OnInit {
  @Input() r: number
  @Input() hits: Array<Hit>
  @Output() rChange = new EventEmitter<number>()
  @Output() hitEvent = new EventEmitter()
  @Output() deleteEvent = new EventEmitter()
  form: FormGroup

  constructor(private http: HttpClient, private router: Router, private httpService: HttpService) {
  }

  ngOnInit() {
    this.form = new FormGroup({
      x: new FormControl(null, [Validators.required, Validators.min(-5), Validators.max(5)]),
      y: new FormControl(null, [Validators.required, Validators.min(-5), Validators.max(3)]),
      r: new FormControl(null, [Validators.required, Validators.min(0.000000001), Validators.max(5)])
    })
  }

  requestHit() {
    if (!this.validate()) {
      return
    }

    this.httpService.checkHit(this.form.value).subscribe(
      {
        next: (data: any) => {
          let hit: Hit = {
            x: data.x,
            y: data.y,
            r: data.r,
            hit: data.hit
          }

          this.hits.push(hit)

          this.hitEvent.emit(hit)
        },
        error: error => {
          if (error.status == 401) {
            localStorage.removeItem('auth-token')
            this.router.navigate(['/login'])
          }
        }
      }
    )


  }

  deleteHits() {
    this.httpService.deleteAllHits().subscribe(
      {
        next: () => {
          this.deleteEvent.emit()
        },
        error: error => {
          if (error.status == 401) {
            localStorage.removeItem('auth-token')
            this.router.navigate(['/login'])
          }
        }
      }
    )

  }

  validate(): boolean {
    if (this.form.value.x == null
      || this.form.value.y == null
      || this.form.value.r == null) {
      alert('Все поля должны быть заполнены')
      return false
    }

    return true
  }

  onChangeR(e: any) {
    this.rChange.emit(e.target.value == "" ? 0 : e.target.value)
  }

  onlyDigits(e: any, minValue: number, maxValue: number) {
    let inputName = e.target.getAttribute('formControlName')

    let separator = ".";
    let replaced = new RegExp('[^\\d\\' + separator + '\\-]', "g");
    let regex = new RegExp('\\' + separator, "g");
    e.target.value = e.target.value.replace(replaced, "");
    let val = parseFloat(separator === "." ? e.target.value : e.target.value.replace(new RegExp(separator, "g"), "."));

    if (minValue <= maxValue) {
      if (e.target.value[0] === "-") {
        if (e.target.value.length > 8) {
          e.target.value = e.target.value.substr(0, 8);
        }
      } else {
        if (e.target.value.length > 7) {
          e.target.value = e.target.value.substr(0, 7);
        }
      }

      if (e.target.value[0] === separator) {
        e.target.value = "0" + e.target.value;
      }

      if (minValue < 0 && maxValue < 0) {
        if (e.target.value[0] !== "-")
          e.target.value = "-" + e.target.value[0];
      } else if (minValue >= 0 && maxValue >= 0) {
        if (e.target.value[0] === "-")
          e.target.value = e.target.value.substr(0, 0);
      }

      if (val < minValue || val > maxValue) {
        e.target.value = e.target.value.substr(0, 0);

      }
      if (e.target.value.match(regex))
        if (e.target.value.match(regex).length > 1) {
          e.target.value = e.target.value.substr(0, 0);
        }

      if (e.target.value.match(/\-/g))
        if (e.target.value.match(/\-/g).length > 1) {
          e.target.value = e.target.value.substr(0, 0);
        }

      if (isNaN(e.target.value) && e.target.value.length > 1) {
        e.target.value = e.target.value.substr(0, 0);
      }

    }

    this.form.value[inputName] = e.target.value
  }

}
