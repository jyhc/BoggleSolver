import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TST;

public class BoggleSolver
{
    private TST<Integer> dict;
    private class Node {
        public boolean marked[][];
        public String string;
        public Node(String ancestor, boolean marked[][]) {
            this.marked = new boolean[marked.length][];
            for(int i = 0; i < marked.length; i++) {
                this.marked[i] = Arrays.copyOf(marked[i], marked[i].length);
            }            
            this.string = ancestor;            
        }
    }
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        dict = new TST<>();
        int points = 0;
        int charNum = 0;
        for(String word : dictionary) {
            charNum = word.length();
            if(charNum >= 0 && charNum <= 2) {
                points = 0;
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
            dict.put(word, points);
        }
        //StdOut.print(dict.size());
        
    } 

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board){
        SET<String> validWords = new SET<>();
        //use ArrayList<String> to allow 
        //repeating words formed by different tiles in Boggle
        for(int i = 0; i < board.rows(); i++) {
            for(int j = 0; j < board.cols(); j++) {
//                Stack<Character> stack = new Stack<>();
                boolean[][] marked = new boolean[board.rows()][board.cols()];//default false
                marked[i][j] = true;
                String rootChar;
                if(board.getLetter(i, j) == 'Q') {
                    rootChar = "QU";
                } else rootChar = String.valueOf(board.getLetter(i, j));
                Node rootNode = new Node(rootChar,marked);                
                boggleDFS(i,j,rootNode,validWords,board);             
            }
        }
        return validWords;
    }
    
    private void boggleDFS(int i, int j, Node root, SET<String> validWords, BoggleBoard board) {
        String temp;
        Node next;
        if(i != 0) {//not top edge
            if(!root.marked[i-1][j]) {//look at above node
                temp = root.string;
                if(board.getLetter(i-1, j) == 'Q') {
                    temp = temp.concat("QU");
                } else temp = temp.concat(String.valueOf(board.getLetter(i-1, j)));
                if(temp.length() >= 3) {
                    if(dict.contains(temp)) validWords.add(temp);
                }
                //if(!((Queue<String>) dict.keysWithPrefix(temp)).isEmpty())
                if(dict.keysWithPrefix(temp).iterator().hasNext()) {
                    next = new Node(temp,root.marked);
                    next.marked[i][j] = true;
                    boggleDFS(i-1, j, next, validWords, board);
                }
            }
            
        }
        
        if(i != board.rows()-1) {//not bottom edge
            if(!root.marked[i+1][j]) {//look at below node
                temp = root.string;
                if(board.getLetter(i+1, j) == 'Q') {
                    temp = temp.concat("QU");
                } else temp = temp.concat(String.valueOf(board.getLetter(i+1, j)));
                if(temp.length() >= 3) {
                    if(dict.contains(temp)) validWords.add(temp);
                }
                if(dict.keysWithPrefix(temp).iterator().hasNext()) {
                    next = new Node(temp,root.marked);
                    next.marked[i][j] = true;
                    boggleDFS(i+1, j, next, validWords, board);
                }
            }
        }
        
        if(j != 0) {//not left edge
            if(!root.marked[i][j-1]) {//look at left node
                temp = root.string;
                if(board.getLetter(i, j-1) == 'Q') {
                    temp = temp.concat("QU");
                } else temp = temp.concat(String.valueOf(board.getLetter(i, j-1)));
                if(temp.length() >= 3) {
                    if(dict.contains(temp)) validWords.add(temp);
                }
                if(dict.keysWithPrefix(temp).iterator().hasNext()) {
                    next = new Node(temp,root.marked);
                    next.marked[i][j] = true;
                    boggleDFS(i, j-1, next, validWords, board);
                }
            }
        }
        
        if(j != board.cols()-1) {//not right edge
            if(!root.marked[i][j+1]) {//look at right node
                temp = root.string;
                if(board.getLetter(i, j+1) == 'Q') {
                    temp = temp.concat("QU");
                } else temp = temp.concat(String.valueOf(board.getLetter(i, j+1)));
                if(temp.length() >= 3) {
                    if(dict.contains(temp)) validWords.add(temp);
                }
                if(dict.keysWithPrefix(temp).iterator().hasNext()) {
                    next = new Node(temp,root.marked);
                    next.marked[i][j] = true;
                    boggleDFS(i, j+1, next, validWords, board);
                }
            }
        }
        
        if(i != 0 && j != 0) {//not top left edge
            if(!root.marked[i-1][j-1]) {//look at top left node
                temp = root.string;
                if(board.getLetter(i-1, j-1) == 'Q') {
                    temp = temp.concat("QU");
                } else temp = temp.concat(String.valueOf(board.getLetter(i-1, j-1)));
                if(temp.length() >= 3) {
                    if(dict.contains(temp)) validWords.add(temp);
                }
                if(dict.keysWithPrefix(temp).iterator().hasNext()) {
                    next = new Node(temp,root.marked);
                    next.marked[i][j] = true;
                    boggleDFS(i-1, j-1, next, validWords, board);
                }
            }
        }
        
        if(i != 0 && j != board.cols()-1) {//not top right edge
            if(!root.marked[i-1][j+1]) {//look at top right node
                temp = root.string;
                if(board.getLetter(i-1, j+1) == 'Q') {
                    temp = temp.concat("QU");
                } else temp = temp.concat(String.valueOf(board.getLetter(i-1, j+1)));
                if(temp.length() >= 3) {
                    if(dict.contains(temp)) validWords.add(temp);
                }
                if(dict.keysWithPrefix(temp).iterator().hasNext()) {
                    next = new Node(temp,root.marked);
                    next.marked[i][j] = true;
                    boggleDFS(i-1, j+1, next, validWords, board);
                }
            }
        }
        
        if(i != board.rows()-1 && j != 0) {//not bottom left edge
            if(!root.marked[i+1][j-1]) {//look at top left node
                temp = root.string;
                if(board.getLetter(i+1, j-1) == 'Q') {
                    temp = temp.concat("QU");
                } else temp = temp.concat(String.valueOf(board.getLetter(i+1, j-1)));
                if(temp.length() >= 3) {
                    if(dict.contains(temp)) validWords.add(temp);
                }
                if(dict.keysWithPrefix(temp).iterator().hasNext()) {
                    next = new Node(temp,root.marked);
                    next.marked[i][j] = true;
                    boggleDFS(i+1, j-1, next, validWords, board);
                }
            }
        }
        
        if(i != board.rows()-1 && j != board.cols()-1) {//not bottom right edge
            if(!root.marked[i+1][j+1]) {//look at top right node
                temp = root.string;
                if(board.getLetter(i+1, j+1) == 'Q') {
                    temp = temp.concat("QU");
                } else temp = temp.concat(String.valueOf(board.getLetter(i+1, j+1)));
                if(temp.length() >= 3) {
                    if(dict.contains(temp)) validWords.add(temp);
                }
                if(dict.keysWithPrefix(temp).iterator().hasNext()) {
                    next = new Node(temp,root.marked);
                    next.marked[i][j] = true;
                    boggleDFS(i+1, j+1, next, validWords, board);
                }
            }
        }
        return;        
    }
    
    
    
    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if(dict.get(word) == null) {
            return 0;
        } else return dict.get(word);
        
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }
}
