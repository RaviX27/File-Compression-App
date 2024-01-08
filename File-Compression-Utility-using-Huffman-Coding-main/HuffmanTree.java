import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {
    private PriorityQueue <HuffmanNode> minHeap;

    public HuffmanTree(){
        //Create a priority Queue
        minHeap = new PriorityQueue<>((a,b) -> a.frequency - b.frequency);
    }

    public HuffmanNode buildHuffmanTree(String data){
        //count frequenceis of character in the data
        Map <Character , Integer> frequencyMap = countFrequencies(data);

        //Build Huffman Tree
        buildPriorityQueue(frequencyMap);

        return buildTree();
    }

    private Map <Character, Integer> countFrequencies (String data){
        Map <Character , Integer > frequencyMap = new HashMap<>();

        for (char c : data.toCharArray()){
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0)+1);
        }

        return frequencyMap;
    }

    private void buildPriorityQueue(Map<Character, Integer>frequencMap){

        for(Map.Entry <Character, Integer> entry : frequencMap.entrySet()){

            HuffmanNode Node = new HuffmanNode(entry.getKey(), entry.getValue());

            minHeap.add(Node);
        }
    }

    private HuffmanNode buildTree(){

        while (minHeap.size() > 1) {

            HuffmanNode left = minHeap.poll();
            HuffmanNode right = minHeap.poll();

            HuffmanNode internalNode = new HuffmanNode('\0', left.frequency + right.frequency);

            internalNode.left = left;
            internalNode.right = right;

            minHeap.add(internalNode);
        }

        return minHeap.poll();

    }


    
}
