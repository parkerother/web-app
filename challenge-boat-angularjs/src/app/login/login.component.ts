import { Component, OnInit } from '@angular/core';
import { RestApiService } from '../servives/rest-api.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username!: string;
  password!: string;
  message!: any;
  errorMessage = 'Invalid Credentials';
  successMessage!: string;
  invalidLogin = false;
  loginSuccess = false;


  constructor(private service: RestApiService, 
    private router: Router,private route: ActivatedRoute) { }

  ngOnInit() {
    

  }

  doLogin() {
    let resp = this.service.login(this.username, this.password);
    resp.subscribe(data => {
      this.message = data;
      this.router.navigate(["/boats"])
    });
    
  }

  handleLogin() {
    this.service.login(this.username, this.password).subscribe((result)=> {
      this.invalidLogin = false;
      this.loginSuccess = true;
      this.successMessage = 'Login Successful.';
      this.router.navigate(['/boats']);
    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
    });      
  }
}