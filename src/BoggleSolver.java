import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.TST;

public class BoggleSolver
{
    private TST<Integer> dict;
    
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        dict = new TST<>();
        int points = 0;
        int charNum = 0;
        for(String word : dictionary) {
            charNum = word.length();
            if(charNum >= 0 && charNum <= 2) {
                break;
            } else if(charNum >= 3 && charNum <= 4) {
                points = 1;
            } else if(charNum == 5) {
                points = 2;
            } else if(charNum == 6) {
                points = 3;
            } else if(charNum == 7) {
                points = 5;
            } else {
                points = 11;
            }
            dict.put(word,points);
        }
        
    }
    
    private class Node{
        
    }
    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board){
        for(int i = 0; i < board.rows(); i++) {
            for(int j = 0; j < board.cols(); j++) {
                Stack<Character> stack = new Stack<>();
                boolean[][] marked = new boolean[board.rows()][board.cols()];//default false
                pushAndMark(i,j,board,stack,marked);               
                
            }
        }
    }
    
    private void pushAndMark(int i, int j, BoggleBoard board, Stack<Character> stack, boolean[][] marked) {
        if(i != 0) {//top edge
            if(!marked[i-1][j]) stack.push(board.getLetter(i-1, j));            
        }
        if(i != board.rows()-1) {//bottom edge
            if(!marked[i+1][j]) stack.push(board.getLetter(i+1, j));
        }
        if(j != 0) {//left edge
            if(!marked[i][j-1]) stack.push(board.getLetter(i, j-1)); 
        }
        if(j != board.cols()-1) {//right edge
            if(!marked[i][j+1]) stack.push(board.getLetter(i, j+1)); 
        }
        board.getLetter(i, j)
        if(marked[])
        stack.push
    }
    
    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        
    }
}
