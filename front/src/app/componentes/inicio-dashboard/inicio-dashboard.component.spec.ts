import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InicioDashboardComponent } from './inicio-dashboard.component';

describe('InicioDashboardComponent', () => {
  let component: InicioDashboardComponent;
  let fixture: ComponentFixture<InicioDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InicioDashboardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InicioDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
