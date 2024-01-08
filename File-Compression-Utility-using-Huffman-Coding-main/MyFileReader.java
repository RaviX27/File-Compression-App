import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileReader {

    public static String readFile(String filePath) throws IOException{
        StringBuilder content = new StringBuilder();
        
        try(FileReader reader = new FileReader(filePath)){
                char[] buffer = new char[1024];
                int bytesRead;
                while ((bytesRead = reader.read(buffer)) != -1) {
                    content.append(buffer, 0, bytesRead);
                }
        }

        return content.toString();

    }

    public static void writeFile(String filePath , String content)throws IOException{
        try(FileWriter writer  = new FileWriter(filePath)){

            writer.write(content);
        }
    }
    
}
