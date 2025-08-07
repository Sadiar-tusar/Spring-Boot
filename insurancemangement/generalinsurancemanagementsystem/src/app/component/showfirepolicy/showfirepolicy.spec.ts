import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Showfirepolicy } from './showfirepolicy';

describe('Showfirepolicy', () => {
  let component: Showfirepolicy;
  let fixture: ComponentFixture<Showfirepolicy>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Showfirepolicy]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Showfirepolicy);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
