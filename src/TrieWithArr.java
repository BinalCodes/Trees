
public class TrieWithArr {
	TrieNode root;
	static class TrieNode{
		TrieNode[] children;
		boolean isWord;
		TrieNode(){
			children = new TrieNode[26];
		}
	}
	public TrieWithArr() {
		root = new TrieNode();
	}
	/*
	 * Inserts a word into Trie
	 */
	public void insert (String word) {
		TrieNode prev = root;
		for(int i = 0; i<word.length(); i++) {
			char singleChar = word.charAt(i);
			if(prev.children[singleChar - 'a'] == null) {
				prev.children[singleChar - 'a'] = new TrieNode();
			}
			prev = prev.children[singleChar - 'a'];
		}
		prev.isWord = true;
	}
	/*
	 * Returns if the word is in the Trie
	 */
	public boolean search(String word) {
		TrieNode curr = root;
		for(int i = 0 ;i <word.length(); i++) {
			char singleChar = word.charAt(i);
			if (curr.children[singleChar -'a'] == null) {
				return false;
			}else {
				curr = curr.children[singleChar -'a'];
			}
		}
		return curr.isWord;
	}
	public boolean startsWith(String prefix) {
		TrieNode curr = root;
		for(int i = 0 ;i <prefix.length(); i++) {
			char singleChar = prefix.charAt(i);
			if (curr.children[singleChar -'a'] == null) {
				return false;
			}else {
				curr = curr.children[singleChar -'a'];
			}
		}
		return true;
	}
	public static void main(String[] args) {
		TrieWithArr trie = new TrieWithArr();
		trie.insert("apple");
		System.out.println("Searching for word app "+trie.search("app"));
		System.out.println("Searching for prefix app "+trie.startsWith("app"));
		System.out.println("Searching for keyword binal "+trie.search("binal"));
		trie.insert("binal");
		trie.insert("bin");
		System.out.println("After inserting, search for keyword binal "+trie.search("binal"));
	}

}
