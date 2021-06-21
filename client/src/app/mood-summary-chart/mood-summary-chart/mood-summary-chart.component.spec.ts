import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoodSummaryChartComponent } from './mood-summary-chart.component';

describe('MoodSummaryChartComponent', () => {
  let component: MoodSummaryChartComponent;
  let fixture: ComponentFixture<MoodSummaryChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoodSummaryChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoodSummaryChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
