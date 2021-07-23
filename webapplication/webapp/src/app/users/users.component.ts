import { Component, OnInit } from '@angular/core';
import { UsersService } from './users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  constructor(private userservice: UsersService) { 
    console.log("Hello");
  }

  ngOnInit(): void {
    console.log("Hello")
    this.userservice.getUsers().subscribe((data: any) => {
      console.log("Data", data.toString());
    });
  }

}
