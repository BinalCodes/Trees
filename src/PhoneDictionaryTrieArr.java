import java.util.ArrayList;

public class PhoneDictionaryTrieArr {
	static class TrieNode{
		TrieNode[] child;
		String word;
		boolean isWord;
		TrieNode(){
			child = new TrieNode[26];
			word = "";
			isWord = false;
		}
	}
	TrieNode root;
	public PhoneDictionaryTrieArr() {
		root = new TrieNode();
	}
	public void buildTrie(String[] dict) {
		for(String word: dict) {
			TrieNode curr = root;
			for(char c : word.toCharArray()) {
				if(curr.child[c -'a'] == null) {
					curr.child[c- 'a'] = new TrieNode();
				}
				curr = curr.child[c -'a'];
			}
			curr.word = word;
			curr.isWord = true;
		}
	}
	public ArrayList<String> dictSearch(String prefix) {
		ArrayList<String> result = new ArrayList<String>();
		TrieNode curr = root;
		for(char c : prefix.toCharArray()) {
			if(curr.child[c-'a'] == null) {
				// word doesnt exist in trie so return empty
				return result;
			}
			else {
				curr= curr.child[c-'a'];
			}
		}
		// now our current pointer points to that trieNode. We need to get all words starting with that prefix using dfs
		dfs(result,curr);
		return result;
	}
	public void dfs(ArrayList<String> result, TrieNode curr) {
		if(curr == null)
			return;
		if(curr.word!="" || curr.isWord) {
			result.add(curr.word);
		}
		for(int i= 0;i< 26 ;i++) {
			dfs(result,curr.child[i]);
		}
	}
	public static void main(String[] args) {
		PhoneDictionaryTrieArr obj = new PhoneDictionaryTrieArr();
		String[] dict = {"work","heaven","hello","halogen","haven","hi","good","lord","ronak","binal","guchli"};
		obj.buildTrie(dict);
		ArrayList<String> res = obj.dictSearch("hi");
		res.forEach(System.out::println);
	}

}
