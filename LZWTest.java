package LZW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LZWTest {

    public List<Integer> compress(String uncompressed){
        List<Integer>result=new ArrayList<>();

        HashMap<String,Integer>dictionary=new HashMap<>();

        for(int i=0;i<256;i++){
            dictionary.put(""+(char)i,i); //initializing map with ascii chars and 0-256 as val
        }

        int dictionarySize=dictionary.size();

        //CARRARCARCAR
        String tempString=""; //stores single chars

        for(char c:uncompressed.toCharArray()){

            String tempString2=tempString+c; //stored concatenated chars

            if(dictionary.containsKey(tempString2)){
                //eg is it contains c..check if it contains ca..then check if it contains car
                tempString=tempString2;
            }else{
                //put the result & put appended string in dictonary
                result.add(dictionary.get(tempString));
                dictionary.put(tempString2,dictionarySize++); //we put appended string
                System.out.println("Inserting string "+tempString2+" with val "+dictionary.get(tempString2));
                tempString=""+c; //reinitialize the string to actual char we are standing at
            }
        }

        if(!tempString.equals("")){
            result.add(dictionary.get(tempString));
        }

        return result;
    }
    public static void main(String[] args) {

        List<Integer> compressed=new LZWTest().compress("CARRARCARCAR");
        System.out.println(compressed);
    }
}
