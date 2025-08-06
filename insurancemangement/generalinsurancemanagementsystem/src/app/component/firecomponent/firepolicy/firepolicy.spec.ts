import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Firepolicy } from './firepolicy';

describe('Firepolicy', () => {
  let component: Firepolicy;
  let fixture: ComponentFixture<Firepolicy>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Firepolicy]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Firepolicy);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
