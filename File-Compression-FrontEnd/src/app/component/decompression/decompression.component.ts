import { Component } from '@angular/core';
import { FileService } from 'src/app/service/file.service';

@Component({
  selector: 'app-decompression',
  templateUrl: './decompression.component.html',
  styleUrls: ['./decompression.component.css']
})
export class DecompressionComponent {

  fileToDecompress!: File;

  constructor(private fileService: FileService) {}

  onFileChange(event: any) {
    this.fileToDecompress = event.target.files[0];
  }

  decompressFile() {
    if (!this.fileToDecompress) {
      // Show alert box to the user if no file is selected
      this.showAlert('Please select a file for decompression');
      return;
    }

    const formData = new FormData();
    formData.append('file', this.fileToDecompress);

    this.fileService.decompressFile(formData).subscribe(
      (result: any) => {
        console.log('Decompressed data:', result);

        this.showAlert(`File decompressed successfully. Decompressed file path: ${result.decompressedFilePath}`);
      },
      (error) => {
        console.error('Error decompressing file:', error);
        this.showAlert(`Error decompressing file: ${error}`);
      }
    );
  }

  private showAlert(message: string): void {
    window.alert(message);
  }
}
