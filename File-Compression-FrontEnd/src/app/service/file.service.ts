import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  private baseUrl = 'http://localhost:8080/api/file';

  constructor(private http : HttpClient) { }

  compressFile(formData : FormData): Observable<any> {
    return this.http.post(`${this.baseUrl}/compress`,formData);
  }

  decompressFile(formData : FormData):Observable<any>{
    return this.http.post(`${this.baseUrl}/decompress`,formData);
  }
}
