public class HuffmanNode {
    int frequency;
    char data;
    HuffmanNode left, right;

    public HuffmanNode(char data, int frequency){
        this.data = data;
        this.frequency = frequency;
        this.left = this.right = null;
    }
}
