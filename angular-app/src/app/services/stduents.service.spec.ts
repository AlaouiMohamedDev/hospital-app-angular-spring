import { TestBed } from '@angular/core/testing';

import { StduentsService } from './stduents.service';

describe('StduentsService', () => {
  let service: StduentsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StduentsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
