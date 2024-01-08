import { Component } from '@angular/core';
import { FileService } from 'src/app/service/file.service';

@Component({
  selector: 'app-decompression',
  templateUrl: './decompression.component.html',
  styleUrls: ['./decompression.component.css']
})
export class DecompressionComponent {

  fileToCompress!: File;

  constructor(private fileService : FileService){}

  onFileChange(event : any){
    this.fileToCompress = event.target.files[0];
  }

  decompressFile(){
    if(this.fileToCompress){
      const formData = new FormData();
      formData.append('file',this.fileToCompress);

      this.fileService.decompressFile(formData).subscribe((result : any)=>{
        console.log('Compressed data:', result);
      },
      (error)=>{
        console.error('Error compressing file:', error);
      }
      );
    }
  }

}
