import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateDataStreamDialogComponent } from './create-data-stream-dialog.component';

describe('CreateDataStreamDialogComponent', () => {
  let component: CreateDataStreamDialogComponent;
  let fixture: ComponentFixture<CreateDataStreamDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateDataStreamDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateDataStreamDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
