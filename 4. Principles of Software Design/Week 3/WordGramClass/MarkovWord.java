

import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord (int order) {
        myOrder = order;
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram keyGram = new WordGram(myText, index, myOrder);
        sb.append(keyGram.toString());
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(keyGram);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            keyGram = keyGram.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        // find the index of any occurence of the word assigned to variable
        // target in the string array words
        
        // returns the index of the target word if found, otherwise returns -1
        
        for (int k=start; k<words.length; k++) {
            if (words[k].equals(target)) {
                return k;
            }
        }
        return -1;
    }
    
    public void testIndexOf() {
        String testString = "this is just a test yes this is a simple test";
        String[] testStringArr = testString.split("\\s+");
        
        
    }
    
    private ArrayList<String> getFollows(WordGram keyGram) {
        // finds the words that directly follow after any occurence of the
        // word assigned to the variable key
        
        // adds these words to and returns the array list 'follows'
        
        // uses the helper function indexOf() to find the index of any
        // occurence of the word assigned to key in a string array
        
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        
        while (pos < myText.length) {
            int start = indexOf(myText, keyGram, pos);
            if (start == -1) {
                break;
            }
            if (start + keyGram.length() > myText.length-1) {
                break;
            }
            String next = myText[start+1];
            follows.add(next);
            pos = start + 1;
        }
        
        return follows;
    }
}
