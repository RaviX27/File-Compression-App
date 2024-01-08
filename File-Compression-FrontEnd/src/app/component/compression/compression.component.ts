import { Component } from '@angular/core';
import { FileService } from 'src/app/service/file.service';

@Component({
  selector: 'app-compression',
  templateUrl: './compression.component.html',
  styleUrls: ['./compression.component.css']
})
export class CompressionComponent {

  fileToCompress!: File;

  constructor(private fileService: FileService) {}

  onFileChange(event: any) {
    this.fileToCompress = event.target.files[0];
  }

  compressFile() {
    if (this.fileToCompress) {
      const formData = new FormData();
      formData.append('file', this.fileToCompress);

      this.fileService.compressFile(formData).subscribe(
        (result: any) => {
          console.log('Compressed data:', result);
        },
        (error) => {
          console.error('Error compressing file:', error);
        }
      );
    }
  }
}
