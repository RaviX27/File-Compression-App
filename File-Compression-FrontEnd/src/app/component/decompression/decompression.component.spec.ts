import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DecompressionComponent } from './decompression.component';

describe('DecompressionComponent', () => {
  let component: DecompressionComponent;
  let fixture: ComponentFixture<DecompressionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DecompressionComponent]
    });
    fixture = TestBed.createComponent(DecompressionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
