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
    if (!this.fileToCompress) {
      // Show alert box to the user if no file is selected
      this.showAlert('Please select a file for compression');
      return;
    }

    const formData = new FormData();
    formData.append('file', this.fileToCompress);

    this.fileService.compressFile(formData).subscribe(
      (result: any) => {
        console.log('Compression success:', result.message);
        console.log('Compressed data:', result.compressedData);
        console.log('Compressed file path:', result.compressedFilePath);

        this.showAlert(`File compressed successfully. Compressed file path: ${result.compressedFilePath}`);
      },
      (error) => {
        console.error('Error compressing file:', error);
        this.showAlert(`Error compressing file: ${error}`);
      }
    );
  }

  private showAlert(message: string): void {
    window.alert(message);
  }
}
