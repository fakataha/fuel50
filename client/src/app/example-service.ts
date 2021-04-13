import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie';
import { filter, map } from 'rxjs/operators';
import { Observable } from 'rxjs';

const PING_URL = 'http://localhost:8080/ping';

@Injectable({
  providedIn: 'root'
})
export class ExampleService {

  constructor(private http: HttpClient) { }

  public getStatus(): Observable<Object> {
    return this.http
      .get(PING_URL,
      {
        withCredentials: true
      });
  }
}


