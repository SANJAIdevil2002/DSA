import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class huffman {
    static class Node{
        char data;
        int freq;
        Node left,right;
        Node(char data,int freq){
            this.data=data;
            this.freq=freq;
            this.left=null;
            this.right=null;
        }
        // parent
        Node(int freq){
            this.data='\0';//it is null character(ascii code is 0), it represent the no character
            this.freq=freq;
            this.left=null;
            this.right=null;
        }
    }

    // frequency
    static List<Node>nodes=new ArrayList<>();
    public static void calculateFrequency(String word){
        Map<Character,Integer> frequency=new HashMap<>();
        for(char ch:word.toCharArray()){
            int freq=0;
            if(!frequency.containsKey(ch))
            {
                for(char ch1:word.toCharArray()){
                      if(ch==ch1){
                        freq++;
                      }
            }
            frequency.put(ch,freq);
            nodes.add(new Node(ch, freq));
            }
        }
    }

    // create the binary tree
    public static Node huffmanBinaryTree(){
        while (nodes.size()>1) {
            nodes.sort((a,b)->a.freq - b.freq);
        Node left=nodes.remove(0);
        Node right=nodes.remove(0);

        Node newNode=new Node(left.freq+right.freq);
        newNode.left=left;
        newNode.right=right;
        nodes.add(newNode);
        }
        return nodes.get(0);//root
    }
    public static void generateHuffmanCode(Node node,String currentData,Map<Character,String> codes ){
        if(node==null){
            return;
        }
        if(node.data!='\0'){
            codes.put(node.data, currentData);
        }
        generateHuffmanCode(node.left, currentData+'0', codes);
        generateHuffmanCode(node.right, currentData+'1', codes);

    }
    // huffmanencoding

    public static Map<Character,String>huffmanEncoding(String word){
        nodes.clear();//initial clear
        calculateFrequency(word);
        Node root=huffmanBinaryTree();
        Map<Character,String>charToCode=new HashMap<>();
        generateHuffmanCode(root, "", charToCode);
        return charToCode;
    }
    public static String huffmandecoding(Map<Character,String> conversionTable,String binaryCode){
        // converted map =>a:10 to10:a
        Map<String,Character>codeTochar=new HashMap<>();
         for(Map.Entry<Character,String> entry:conversionTable.entrySet()){
            codeTochar.put(entry.getValue(),entry.getKey());
        } 
        StringBuffer currentChar=new StringBuffer();
        StringBuffer decodedWord=new StringBuffer();
        for(char ch:binaryCode.toCharArray()){
            currentChar.append(ch);
            if(codeTochar.containsKey(currentChar.toString())){
                   decodedWord.append(codeTochar.get(currentChar.toString()));
                   currentChar.setLength(0);
            }
            
        }
        return decodedWord.toString();
       
    }
    public static void main(String[] args) {
        String word="lossless";
        Map<Character,String>conversionTable=huffmanEncoding(word);
        StringBuffer sb=new StringBuffer();
        for(char d:word.toCharArray()){
           if(conversionTable.containsKey(d)){
            sb.append(conversionTable.get(d));
           }
        }
        String encodedCode=sb.toString();
    System.out.println(encodedCode);
        // decoded
        System.out.println(huffmandecoding(conversionTable, encodedCode));
    }
}
