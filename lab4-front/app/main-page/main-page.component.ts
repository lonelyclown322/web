import {Component, OnInit, ViewChild} from '@angular/core';
import {GraphComponent} from "./graph/graph.component";
import {Hit} from "../shared/interfaces/hit";
import {HttpService} from "../shared/services/http-services.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  R: number = 0
  hits: Array<Hit>
  @ViewChild(GraphComponent) graph: GraphComponent

  constructor(private httpService: HttpService, private router: Router) {
  }

  ngOnInit(): void {

    this.hits = new Array<Hit>()

    this.httpService.getAllHits().subscribe(
      {
        next: (data: any) => {
          if (data) {
            for (const hit of data) {
              this.hits.push(hit)
            }

            this.graph.drawPoints()
          }
        },
        error: error => {
          if (error.status == 401) {
            localStorage.removeItem('auth-token')
            this.router.navigate(['/login'])
          }
        }
      }
    )

    // let options = {
    //   headers: {
    //     'Authorization': 'Bearer ' + localStorage.getItem('auth-token')
    //   }
    // }
    //
    //
    // this.http.post<{ message: string }>('http://localhost:8080/hits', '', options)
    //   .pipe()
    //   .subscribe((res) => {
    //     for (let dotJson of res.message) {
    //       let dotObj = dotJson as unknown as { dot: string }
    //       let dot = dotObj.dot
    //       let parsed = dot as unknown  as { x: number, y: number, r: number, hitted: string }
    //       this.hits.push(
    //         {
    //           x: Number(parsed.x),
    //           y: Number(parsed.y),
    //           r: Number(parsed.r),
    //           hit: Boolean(JSON.parse(parsed.hitted))
    //         }
    //       )
    //     }
    //
    //     this.graph.drawPoints()
    //
    //   }, (error) => {
    //     console.log(error)
    //     if (error.status == 401) {
    //       localStorage.removeItem('auth-token')
    //     }
    //   })
  }

}
