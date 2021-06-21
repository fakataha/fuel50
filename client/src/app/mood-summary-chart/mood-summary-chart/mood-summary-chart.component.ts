import { Component, OnInit } from '@angular/core';
import * as Highcharts from "highcharts";
import {MoodSummary} from "../../mood-track-container/mood-summary";
import {MoodTrackerService} from "../../mood-tracker.service";

// Taken from https://www.eduforbetterment.com/polar-chart-in-angular-using-highchart/
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

    pane: {
      startAngle: 0,
      endAngle: 360
    },

    xAxis: {
      tickInterval: 45,
      min: 0,
      max: 360,
      labels: {
        format: '{value}°'
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
          this.options.series[0] = result.happy;
          this.options.series[1] = result.justNormalReally;
          this.options.series[2] = result.aBitMeh;
          this.options.series[3] = result.grumpy;
          this.options.series[4] = result.stressedOutNotAHappyCamper;
          Highcharts.chart('container', this.options);
        });
  }
}
