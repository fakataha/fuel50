import { Component, OnInit } from '@angular/core';
import { MoodTrackerService } from '../mood-tracker.service';
import { CookieService } from 'ngx-cookie';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';

const COOKIE_NAME = 'moodie-cookie';

@Component({
  selector: 'mood-track-container',
  templateUrl: './mood-track-container.component.html',
  styleUrls: ['./mood-track-container.component.css']
})
export class MoodTrackContainerComponent implements OnInit {

  private online: boolean;
  private moodForm: FormGroup;
  private submitted: boolean;
  private summarize: boolean;

  constructor(private moodTrackerService: MoodTrackerService, private cookieService: CookieService, private fb: FormBuilder) {
  }

  ngOnInit() {
    this.refreshData();
    this.initializeForm();
  }

  setCookies(): void {
    const today = new Date();
    this.cookieService.put(COOKIE_NAME, today.toUTCString());
  }

  initializeForm(): void {
    this.moodForm = this.fb.group({
      moodMessage : [null, Validators.max(350)],
      moods : [null, Validators.required]
    });
  }

  refreshData() {
    this.moodTrackerService.getStatus()
      .subscribe(result => {
        this.online = true;
      });
  }

  onSubmit(): void {
    this.checkCookie();
    if (!this.submitted) {
      const moodRecord = {
        mood: this.moodForm.controls['moods'].value,
        moodMessage: this.moodForm.controls['moodMessage'].value
      };
      console.log(moodRecord);
      this.moodTrackerService.submit(moodRecord)
      .subscribe(result => {
        this.setCookies();
        this.summarize = true;
      },
  error => {
          console.log(error);
      });
    }
  }

  private checkCookie() {
    const lastSubmission = new Date(this.cookieService.get(COOKIE_NAME));
    const startOfDayUTCDate = new Date();
    const endOfDayUTCDate = new Date();
    endOfDayUTCDate.setUTCHours(23, 59, 0);
    startOfDayUTCDate.setUTCHours(0, 0, 0);
    this.submitted = startOfDayUTCDate < lastSubmission && lastSubmission < endOfDayUTCDate;
  }
}
