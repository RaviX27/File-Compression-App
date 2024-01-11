package com.fileCompression.huffmanbackend.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileCompression.huffmanbackend.HuffmanCoding;
import com.fileCompression.huffmanbackend.HuffmanNode;
import com.fileCompression.huffmanbackend.HuffmanTree;
import com.fileCompression.huffmanbackend.MyFileReader;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @PostMapping("/compress")
    public ResponseEntity<Map<String, String>> compressFile(@RequestBody MultipartFile file){

        try {
            // Check if the file is not empty
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Uploaded file is empty"));
            }

            // Read the content of the file
            String originalData = new String(file.getBytes());

            // Handle Huffman tree and compression logic here
            HuffmanTree huffmanTree = new HuffmanTree();
            HuffmanNode root = huffmanTree.buildHuffmanTree(originalData);

            HuffmanCoding huffmanCoding = new HuffmanCoding();
            huffmanCoding.generateHuffmanCode(root, "");

            String compressedData = huffmanCoding.compress(originalData);

            // Save the compressed data to a file
            String compressedFilePath = saveToFile(compressedData, "compressed_file.txt");

            // Return the file path or handle it as needed
            return ResponseEntity.ok().body(Map.of(
                "message", "File compressed successfully",
                "compressedData", compressedData,
                "compressedFilePath", compressedFilePath));

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Error compressing file: " + e.getMessage()));
        }
    }
    

    @PostMapping("/decompress")
    public String decompressFile(@RequestBody MultipartFile file){
        try {
            // Check if the file is not empty
            if (file.isEmpty()) {
                return "Uploaded file is empty";
            }

            // Read the content of the file
            String compressedData = new String(file.getBytes());

            // Assuming you have a HuffmanNode, modify this part based on your actual implementation
            HuffmanTree huffmanTree = new HuffmanTree();
            HuffmanNode root = huffmanTree.buildHuffmanTree(compressedData);

            // Handle Huffman coding and decompression logic here
            HuffmanCoding huffmanCoding = new HuffmanCoding();
            String decompressedData = huffmanCoding.decompress(compressedData, root);

            // Return the decompressed data or handle it as needed
            return decompressedData;

        } catch (IOException e) {
            e.printStackTrace();
            return "Error decompressing file: " + e.getMessage();
        }

    }

    private String saveToFile(String content, String fileName) throws IOException {
        String filePath = "F:\\Projects\\Data Structure project" + fileName; // specify the desired path
        MyFileReader.writeFile(filePath, content);
        return filePath;
    }
    
}
