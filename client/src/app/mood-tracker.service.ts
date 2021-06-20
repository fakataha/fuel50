import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import { CookieService } from 'ngx-cookie';
import {catchError, filter, map} from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import {MoodRecord} from "./mood-track-container/mood-record";
import {MoodSummary} from "./mood-track-container/mood-summary";

const BASE_URL = 'http://localhost:8081/api/v1';
const PING_URL = BASE_URL + '/ping';
const SUMMARY_URL = BASE_URL + '/mood/summary';
const SUBMIT_URL = BASE_URL + '/mood';

@Injectable({
  providedIn: 'root'
})
export class MoodTrackerService {

  constructor(private http: HttpClient) { }

  public getStatus(): Observable<Object> {
    return this.http
      .get(PING_URL,
      {
        withCredentials: true
      });
  }

  public getSummary(): Observable<MoodSummary> {
    return this.http
        .get<MoodSummary>(SUMMARY_URL, {
          withCredentials: true
        });
  }

  public submit(moodRecord: MoodRecord): Observable<MoodRecord> {
    return this.http
        .post<MoodRecord>(SUBMIT_URL, moodRecord)
        .pipe(
            catchError(this.handleError)
        );
  }

  handleError(error: HttpErrorResponse) {
      if (error.status === 0) {
          // A client-side or network error occurred. Handle it accordingly.
          console.error('An error occurred:', error.error);
      } else {
          // The backend returned an unsuccessful response code.
          // The response body may contain clues as to what went wrong.
          console.error(
              `Backend returned code ${error.status}, ` +
              `body was: ${error.error}`);
      }
      // Return an observable with a user-facing error message.
      return throwError(
          'Something bad happened; please try again later.');
  }
}


