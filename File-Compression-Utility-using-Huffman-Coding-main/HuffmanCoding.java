import java.util.HashMap;
import java.util.Map;

public class HuffmanCoding {
    private Map<Character, String> huffmanCodes;

    public HuffmanCoding(){
        huffmanCodes = new HashMap<>();
    }

    public void generateHuffmanCode(HuffmanNode root , String code){
        traverseTree(root,code);
    }

    public void traverseTree(HuffmanNode node, String code){
        //Base Case 
        if(node.left == null && node.right == null){
            huffmanCodes.put(node.data, code);
        }
        //Recusive Case
        if(node.left != null){
            traverseTree(node.left, code + "0");
        }
        if(node.right != null){
            traverseTree(node.right, code + "1");
        }
    }

    public String compress (String data){
        StringBuilder compressedData = new StringBuilder();

        for(char c : data.toCharArray()){
            compressedData.append(huffmanCodes.get(c));
        }
        return compressedData.toString();
    }

    public String decompress (String compressesData, HuffmanNode root){
        StringBuilder decompressedData = new StringBuilder();
        HuffmanNode currentNode = root;

        for(char bit : compressesData.toCharArray()){
            if (bit == '0'){
                currentNode = currentNode.left;
            }
            else if(bit == '1'){
                currentNode = currentNode.right;
            }

            if(currentNode.left == null && currentNode.right == null){
                decompressedData.append(currentNode.data);
                currentNode = root;
            }
        }
        return decompressedData.toString();   
    }
}
