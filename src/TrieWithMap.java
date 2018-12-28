import java.util.HashMap;
import java.util.Map;


public class TrieWithMap {
	TrieNode root;
	static class TrieNode{
		Map<Character, TrieNode> children;
		boolean isWord;
		TrieNode(){
			children = new HashMap<Character, TrieNode>();
		}
	}
	TrieWithMap(){
		root = new TrieNode();
	}
	/*
	 * Inserts a word into Trie
	 */
	public void insert (String word) {
		TrieNode curr = root;
		for (char c : word.toCharArray()) {
			if(!curr.children.containsKey(c)) {
				//insert into the map
				curr.children.put(c, new TrieNode());
			}
			curr = curr.children.get(c);
		}
		curr.isWord = true;
	}
	public boolean search (String word) {
		TrieNode curr = root;
		for(char c: word.toCharArray()) {
			if(!curr.children.containsKey(c)) {
				return false;
			}
			else {
				curr = curr.children.get(c);
			}
		}
		return curr.isWord;
	}
	public boolean startsWith(String prefix) {
		TrieNode curr = root;
		for(char c: prefix.toCharArray()) {
			if(!curr.children.containsKey(c)) {
				return false;
			}
			else {
				curr = curr.children.get(c);
			}
		}
		return true;
	}
	public static void main(String[] args) {
		TrieWithMap trie = new TrieWithMap();
		trie.insert("apple");
		System.out.println("Searching for word app "+trie.search("app"));
		System.out.println("Searching for prefix app "+trie.startsWith("app"));
		System.out.println("Searching for keyword binal "+trie.search("binal"));
		trie.insert("binal");
		trie.insert("bin");
		System.out.println("After inserting, search for keyword binal "+trie.search("binal"));
	}

}
