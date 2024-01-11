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
          console.log('Compression success:', result.message);
          console.log('Compressed data:', result.compressedData);
          console.log('Compressed file path:', result.compressedFilePath);
        },
        (error) => {
          console.error('Error compressing file:', error);
        }
      );
    }
  }
}
