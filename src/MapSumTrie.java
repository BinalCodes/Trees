import java.util.HashMap;
import java.util.Map;


/*
 * Implement a MapSum class with insert, and sum methods.

	For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.
	
	For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.
	
	Example 1:
	Input: insert("apple", 3), Output: Null
	Input: sum("ap"), Output: 3
	Input: insert("app", 2), Output: Null
	Input: sum("ap"), Output: 5
 */
public class MapSumTrie {
	TrieNode root;
	static class TrieNode{
		Map<Character, TrieNode> children;
		boolean isWord;
		int val;
		TrieNode(){
			children = new HashMap<>();
			isWord = false;
			this.val = 0;
		}
	}
	public MapSumTrie() {
		root = new TrieNode();
	}
	public void insert (String key, int val) {
		TrieNode curr = root;
		for(char c : key.toCharArray()) {
			TrieNode next = curr.children.get(c);
			if(next == null) // char not present
			{
				next = new TrieNode();
				curr.children.put(c, next);
			}
			curr = next;
		}
		curr.isWord = true;
		curr.val = val;
	}
	public int sum(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
	    TrieNode next = curr.children.get(c);
	    if (next == null) {
	        return 0;
	    }
	    curr = next;
        }
		
        return dfs(curr);
    }
    
    private int dfs(TrieNode root) {
        int sum = 0;
        for (char c : root.children.keySet()) {
            sum += dfs(root.children.get(c));
        }
        return sum + root.val;
    }
	public static void main(String[] args) {
		MapSumTrie map = new MapSumTrie();
		map.insert("apple", 3);
		map.insert("ape", 4);
		System.out.println("Map sum is "+map.sum("ap"));

	}

}
