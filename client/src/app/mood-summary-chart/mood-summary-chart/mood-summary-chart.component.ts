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
      polar: true,
      type: 'line'
    },

    title: {
      text: 'Mood Summary'
    },

    xAxis: {
      tickmarkPlacement: 'on',
      lineWidth: 0,
      categories: ['Happy', 'Just normal really', 'A bit "meh"', 'Grumpy', 'Stressed out, not a happy camper'],
      labels: {
        format: '{value}'
      }
    },

    yAxis: {
      min: 0
    },

    plotOptions: {
      series: {
        pointStart: 0,
        pointInterval: 45
      },
      column: {
        pointPadding: 0,
        groupPadding: 0
      }
    },

    series: [{
      type: 'line',
      name: 'Happy',
      data: [],
    },
      {
        type: 'line',
        name: 'Just normal really',
        data: [],
      },
      {
        type: 'line',
        name: 'A bit "meh"',
        data: [],
      },
      {
        type: 'line',
        name: 'Grumpy',
        data: [],
      },
      {
        type: 'line',
        name: 'Stressed out, not a happy camper',
        data: [],
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
          console.log(result);
          this.options.series[0]['data']  = result.happy;
          this.options.series[1]['data']  = result.justNormalReally;
          this.options.series[2]['data']  = result.aBitMeh;
          this.options.series[3]['data']  = result.grumpy;
          this.options.series[4]['data']  = result.stressedOutNotAHappyCamper;
          Highcharts.chart('container', this.options);
        });
  }
}
