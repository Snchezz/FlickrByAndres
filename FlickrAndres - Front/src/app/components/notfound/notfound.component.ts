import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-notfound',
  templateUrl: './notfound.component.html',
  styleUrls: ['./notfound.component.css']
})
export class NotfoundComponent implements OnInit {

  constructor(private title:Title) { }

  ngOnInit(): void {
    this.title.setTitle("Flickr Andres - 404")
  }
  

}
function anime(arg0: { targets: string; translateY: number; autoplay: boolean; loop: boolean; easing: string; direction: string; }) {
  throw new Error('Function not implemented.');
}

