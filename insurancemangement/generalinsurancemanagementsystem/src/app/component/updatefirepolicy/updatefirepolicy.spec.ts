import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Updatefirepolicy } from './updatefirepolicy';

describe('Updatefirepolicy', () => {
  let component: Updatefirepolicy;
  let fixture: ComponentFixture<Updatefirepolicy>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Updatefirepolicy]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Updatefirepolicy);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
