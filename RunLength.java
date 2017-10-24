package RunLengthEncoding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunLength {
   //aaaabbcdddeee -> 4a2b1c3d3e
    public void encode(String text){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<text.length();i++){
            int runLength=1;

            while((i+1)<text.length() && text.charAt(i)==text.charAt(i+1)){
                //condn 1:make sure we dont reach end of string index
                runLength++;
                i++;
            }
            sb.append(runLength);
            sb.append(text.charAt(i));
        }
        print(sb.toString());
    }

    public void decode(String string){
       StringBuilder sb=new StringBuilder();
       // 4a2b1c3d3e
        int count=0;
       for(char c:string.toCharArray()){
           if(Character.isDigit(c)){
               count=Integer.parseInt(String.valueOf(c));
           }else{
               while(count>0){
                   sb.append(c);
                   count--;
               }
           }
       }
       print(sb.toString());
    }

    public void decodeWithPatterMatching(String string){

        //for understanding pattern matching visit
        //https://www.javaworld.com/article/3188545/learn-java/java-101-regular-expressions-in-java-part-1.html?page=2
        //https://www.tutorialspoint.com/java/java_regular_expressions.htm
        StringBuilder sb=new StringBuilder();

        // | matches either a or b...we use plus..because number can also be more than 9..say 12a13b
        //for 4a...so it will first encounter 4
        //......then when we do find again..it will encounter a
        Pattern p=Pattern.compile("[0-9]+|[a-zA-Z]");
        //or
       // Pattern p=Pattern.compile("([0-9]+)([a-zA-Z])"); //  int num=Integer.parseInt(matcher.group(1));
        //sb.append(matcher.group(2));...here we have grouped the req..so these are the chnges reqd & remove matcher.find() in betwn

        Matcher matcher=p.matcher(string);

        while(matcher.find()){
            //->4
            int num=Integer.parseInt(matcher.group());
            matcher.find();//->a
            while(num>0){
                sb.append(matcher.group());
                num--;
            }
        }

        print(sb.toString());
    }

    public void print(String text){
        System.out.println(text);
    }
}
