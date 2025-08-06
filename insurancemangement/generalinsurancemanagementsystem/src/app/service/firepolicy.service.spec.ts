import { TestBed } from '@angular/core/testing';

import { FirepolicyService } from './firepolicy.service';

describe('FirepolicyService', () => {
  let service: FirepolicyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FirepolicyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
