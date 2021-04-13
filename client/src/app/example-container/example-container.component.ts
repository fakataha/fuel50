import { Component, OnInit } from '@angular/core';
import { ExampleService } from '../example-service';
import { mergeMap } from 'rxjs/operators';
import { CookieService } from 'ngx-cookie';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-example-container',
  templateUrl: './example-container.component.html',
  styleUrls: ['./example-container.component.css']
})
export class ExampleContainerComponent implements OnInit {

  private online: boolean;

  constructor(private exampleService: ExampleService, private cookieService: CookieService) {
  }

  ngOnInit() {
    this.refreshData();
  }

  refreshData() {
    this.exampleService.getStatus()
      .subscribe(result => {
        this.online = true;
      });
  }
}
