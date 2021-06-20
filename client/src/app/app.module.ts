import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CookieModule } from 'ngx-cookie';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MoodTrackContainerComponent } from './mood-track-container/mood-track-container.component';

@NgModule({
  declarations: [
    AppComponent,
    MoodTrackContainerComponent
  ],
  imports: [
    BrowserModule,
    CookieModule.forRoot(),
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
