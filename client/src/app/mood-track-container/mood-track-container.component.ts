import { Component, OnInit } from '@angular/core';
import { MoodTrackerService } from '../mood-tracker.service';
import { mergeMap } from 'rxjs/operators';
import { CookieService } from 'ngx-cookie';
import {FormGroup, ReactiveFormsModule, FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-example-container',
  templateUrl: './mood-track-container.component.html',
  styleUrls: ['./mood-track-container.component.css']
})
export class MoodTrackContainerComponent implements OnInit {

  private online: boolean;
  moodForm: FormGroup;

  constructor(private moodTrackerService: MoodTrackerService, private cookieService: CookieService, private fb: FormBuilder) {
  }

  ngOnInit() {
    this.refreshData();
    this.initializeForm();
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
    console.log(this.moodForm);
    this.moodTrackerService.submit({
      creationDateTime: null,
      mood: this.moodForm.controls['moods'].value,
      moodMessage : this.moodForm.controls['moodMessage'].value
    })
    .subscribe( result => {
      console.log(result);
    });
  }
}
