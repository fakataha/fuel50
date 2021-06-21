import { Component, OnInit } from '@angular/core';
import * as Highcharts from "highcharts";
import {MoodSummary} from "../../mood-track-container/mood-summary";
import {MoodTrackerService} from "../../mood-tracker.service";

// Referenced from https://www.eduforbetterment.com/polar-chart-in-angular-using-highchart/
declare var require: any;
const More = require('highcharts/highcharts-more');
More(Highcharts);

const Exporting = require('highcharts/modules/exporting');
Exporting(Highcharts);

const ExportData = require('highcharts/modules/export-data');
ExportData(Highcharts);

const Accessibility = require('highcharts/modules/accessibility');
Accessibility(Highcharts);

@Component({
  selector: 'app-mood-summary-chart',
  templateUrl: './mood-summary-chart.component.html',
  styleUrls: ['./mood-summary-chart.component.css']
})
export class MoodSummaryChartComponent implements OnInit {

  public options: any = {

    chart: {
      polar: true
    },

    title: {
      text: 'Mood Summary'
    },

    xAxis: {
      min: 0,
      tickInterval: 45,
      categories: ['Happy', 'Just normal really', 'A bit meh', 'Grumpy', 'Stressed out, not a happy camper']
    },

    legend: {
      enabled: false
    },

    yAxis: {
      min: 0
    },

    series: [
      {
        name: 'People',
        data: []
      }]

  };

  constructor(private moodTrackerService: MoodTrackerService) {

  }

  ngOnInit() {
    this.showSummary();
  }


  showSummary() {
    this.moodTrackerService.getSummary()
        .subscribe(result => {
          this.options.series[0]['data'] = [
              result.happy,
              result.justNormalReally,
              result.abitMeh,
              result.grumpy,
              result.stressedOutNotAHappyCamper
          ];
          console.log(this.options);
          Highcharts.chart('container', this.options);
        },
        error => {
          console.log(error);
        });
  }
}
