import java.util.*;
public class WordDictionary {

	    /** Initialize your data structure here. */
	    Map<Integer, Set<String>> mappings;
	    public WordDictionary() {
	       mappings = new HashMap<Integer, Set<String>>();
	    }
	    
	    /** Adds a word into the data structure. */
	    public void addWord(String word) {
	        int len = word.length();
	        if(mappings.containsKey(len)){
	            mappings.get(len).add(word);
	        }else{
	            HashSet<String> s = new HashSet<String>();
	            s.add(word);
	            mappings.put(len,s);
	        }
	    }
	    
	    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
	    public boolean search(String word) {
	        int len = word.length();//length of the search word
	        if(!mappings.containsKey(len)){
	            return false;//searched word not found
	        }
	        HashSet<String> set1 = (HashSet<String>) mappings.get(len);
	        for(String s: set1){
	            if(isSame(s, word))
	                return true;
	        }
	        return false;
	    }
	    public boolean isSame(String s, String search){
	        for(int i =0 ;i<s.length(); i++){
	            if(search.charAt(i)!='.' && search.charAt(i) != s.charAt(i))
	                return false;
	        }
	        return true;
	    }

	/**
	 * Your WordDictionary object will be instantiated and called as such:
	 * WordDictionary obj = new WordDictionary();
	 * obj.addWord(word);
	 * boolean param_2 = obj.search(word);
	 */
	public static void main(String[] args) {
		WordDictionary w = new WordDictionary();
		w.addWord("apple");
		w.addWord("bad");
		w.addWord("mad");
		w.addWord("dad");
		System.out.println("Search for pattern ba. "+w.search("ba."));
		System.out.println("Search for pattern ba.. "+w.search("ba.."));
		System.out.println("Search for pattern app "+w.search("app"));

	}

}
