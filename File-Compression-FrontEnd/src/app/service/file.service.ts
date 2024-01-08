import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  private baseUrl = 'http:// ';

  constructor(private http : HttpClient) { }

  compressFile(formData : FormData){
    return this.http.post(`${this.baseUrl}/compress`,formData);
  }

  decompressFile(formData : FormData){
    return this.http.post(`${this.baseUrl}/decompress`,formData);
  }
}
