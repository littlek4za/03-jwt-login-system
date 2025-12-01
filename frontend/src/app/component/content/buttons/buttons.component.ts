import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-buttons',
  templateUrl: './buttons.component.html',
  styleUrls: ['./buttons.component.css']
})
export class ButtonsComponent implements OnInit {

  @Output() loginEvent = new EventEmitter();
  @Output() logoutEvent = new EventEmitter();
  @Output() debugEvent = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

}
