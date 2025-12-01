import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DebugContentComponent } from './debug-content.component';

describe('DebugContentComponent', () => {
  let component: DebugContentComponent;
  let fixture: ComponentFixture<DebugContentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DebugContentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DebugContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
