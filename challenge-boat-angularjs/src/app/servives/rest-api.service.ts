import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Boat } from '../model/boat';


const baseUrl = 'http://localhost:8080/boats';


@Injectable({
  providedIn: 'root'
})
export class RestApiService {

  username='user'
  password='password' ;
  storeheader!: HttpHeaders;
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'


  constructor(private http: HttpClient) { }
  
  storeHearderAuth(){
    const headers= this.storeheader;
    return headers
  }

  authenticationService(username: String, password: String) {
    return this.http.get(`http://localhost:8080/`,
      { headers: { authorization: this.createBasicAuthToken(username, password) } }).pipe(map((res) => {
        return this.registerSuccessfulLogin(this.username, this.password);
      }));
  }

  createBasicAuthToken(username: String, password: String) {
    return 'Basic ' + window.btoa(username + ":" + password)
  }

  registerSuccessfulLogin(username: string , password: String) {
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username)
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    this.username= '';
    this.password = '';
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return false
    return true
  }

  getLoggedInUserName() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return ''
    return user
  }


  login(username: string, password: string) {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.http.get(baseUrl, { headers, responseType: 'text' as 'json' })
  }

  getUsers() {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.username + ':' + this.password) });

    return this.http.get("http://localhost:8080/getUsers", { headers });
  }

  getAll(): Observable<Boat[]> {

    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.username + ':' + this.password) });

    return this.http.get<Boat[]>(baseUrl, { headers });
  }

  get(id: any): Observable<Boat> {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.username + ':' + this.password) });

    return this.http.get<Boat>(`${baseUrl}/${id}`, { headers });
  }

  create(data: any): Observable<any> {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.username + ':' + this.password) });

    return this.http.post(baseUrl, data, { headers });
  }

  update(id: any, data: any): Observable<any> {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.username + ':' + this.password) });

    return this.http.put(`${baseUrl}/${id}`, data, { headers });
  }
  delete(id: any): Observable<any> {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.username + ':' + this.password) });

    return this.http.delete(`${baseUrl}/${id}`, { headers });
  }

  deleteAll(): Observable<any> {

    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.username + ':' + this.password) });

    return this.http.delete(baseUrl, { headers });
  }

  findByName(name: any): Observable<Boat[]> {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.username + ':' + this.password) });
    return this.http.get<Boat[]>(`${baseUrl}?name=${name}`, { headers });
  }
}