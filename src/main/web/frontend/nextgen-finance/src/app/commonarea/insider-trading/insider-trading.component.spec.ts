import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsiderTradingComponent } from './insider-trading.component';

describe('InsiderTradingComponent', () => {
  let component: InsiderTradingComponent;
  let fixture: ComponentFixture<InsiderTradingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InsiderTradingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InsiderTradingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
