import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CookieModule } from 'ngx-cookie';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HighchartsChartModule } from 'highcharts-angular';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MoodTrackContainerComponent } from './mood-track-container/mood-track-container.component';
import { MoodSummaryChartComponent } from './mood-summary-chart/mood-summary-chart/mood-summary-chart.component';

@NgModule({
  declarations: [
    AppComponent,
    MoodTrackContainerComponent,
    MoodSummaryChartComponent
  ],
  imports: [
    BrowserModule,
    CookieModule.forRoot(),
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HighchartsChartModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
