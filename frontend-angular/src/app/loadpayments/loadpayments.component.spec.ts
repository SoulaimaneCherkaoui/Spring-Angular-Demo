import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadpaymentsComponent } from './loadpayments.component';

describe('LoadpaymentsComponent', () => {
  let component: LoadpaymentsComponent;
  let fixture: ComponentFixture<LoadpaymentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoadpaymentsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LoadpaymentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
