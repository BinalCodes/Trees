import java.util.*;
public class WordSearchTrie {

	public static void main(String[] args) {
		WordSearchTrie t = new WordSearchTrie();
		char[][] board =
			{
				  {'o','a','a','n'},
				  {'e','t','a','e'},
				  {'i','h','k','r'},
				  {'i','f','l','v'}
			};
		String[] words = {"oath","pea","eat","rain"};
		List<String> res = t.findWords(board, words);
		t.printList(res);
	}
	    static class TrieNode{
	        TrieNode[] children;
	        String word;
	        
	        TrieNode(){
	            children = new TrieNode[26];
	        }
	    }
	    TrieNode root;
	    WordSearchTrie(){
	        root = new TrieNode();
	    }
	    public void printList(List<String> res) {
	    	System.out.println("Ans is: ");
	    	for(String s : res) {
	    		System.out.print(s+" ");
	    	}
	    }
	    public TrieNode buildTrie(String[] words){
	        for(String word : words){
	            TrieNode curr = root;
	            for(int i= 0; i <word.length(); i++){
	                if(curr.children[word.charAt(i) - 'a'] == null){
	                    curr.children[word.charAt(i) - 'a'] = new TrieNode();
	                }
	                curr = curr.children[word.charAt(i) - 'a'];
	            }
	            curr.word = word;
	        }
	        return root;
	    }
	    public List<String> findWords(char[][] board, String[] words) {
	        TrieNode root = buildTrie(words);
	        List<String> res = new ArrayList<String>();
	        for(int i =0; i< board.length ; i++){
	            for(int j=0; j< board[0].length; j++){
	                char c = board[i][j];
	                if(root.children[c-'a'] ==null)
	                    continue;
	                //boolean visited[][] = new boolean[board.length][board[0].length];
	                //TrieNode p = root;
	                findWordsUtil(board,i,j,res,root);
	            }
	        }
	        return res;
	    }
	    public void findWordsUtil(char[][] board, int i, int j, List<String> res, TrieNode p){
	        char c = board[i][j];
	        if(c =='#' || p.children[c -'a'] == null )
	            return;
	        p = p.children[c-'a'];
	        if(p.word!=null){//if its a string
	            res.add(p.word);
	            p.word = null;//so that it doesnt duplicate
	        }
	        board[i][j]= '#';
	        if (i > 0) findWordsUtil(board, i - 1, j , res,p); 
	        if (j > 0) findWordsUtil(board, i, j - 1, res,p);
	        if (i < board.length - 1) findWordsUtil(board, i + 1, j, res,p); 
	        if (j < board[0].length - 1) findWordsUtil(board, i, j + 1, res,p); 
	        board[i][j] = c;
	    }

}
