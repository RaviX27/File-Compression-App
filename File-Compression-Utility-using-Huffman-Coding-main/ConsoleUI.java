import java.io.IOException;
import java.util.Scanner;

public class ConsoleUI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 'C' to compress or 'D' to decompress: ");
        char choice = scanner.nextLine().toUpperCase().charAt(0);

        System.out.print("Enter the path of the file: ");
        String filePath = scanner.nextLine();
        scanner.close();

        try {
            String originalData = MyFileReader.readFile(filePath);

            HuffmanTree huffmanTree = new HuffmanTree();
            HuffmanNode root = huffmanTree.buildHuffmanTree(originalData);

            HuffmanCoding huffmanCoding = new HuffmanCoding();
            huffmanCoding.generateHuffmanCode(root, "");

            String resultData = "";
            String resultFilePath = "";

            if (choice == 'C') {
                // Compressing data
                resultData = huffmanCoding.compress(originalData);
                resultFilePath = filePath + ".compressed";
                System.out.println("Compressed data: " + resultData);
                System.out.println("Compressed data saved to: " + resultFilePath);
                
            } else if (choice == 'D') {
                // Decompressing data
                resultData = MyFileReader.readFile(filePath + ".compressed");
                resultData = huffmanCoding.decompress(resultData, root);
                resultFilePath = filePath + ".decompressed";
                System.out.println("Decompressed data: " + resultData);
                System.out.println("Decompressed data saved to: " + resultFilePath);

            } else {
                System.out.println("Invalid choice. Please enter 'C' to compress or 'D' to decompress.");
                return;
            }

            // Save the result to the specified file
            MyFileReader.writeFile(resultFilePath, resultData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
