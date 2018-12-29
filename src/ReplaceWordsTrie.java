import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ReplaceWordsTrie {
	TrieNode root;
	static class TrieNode{
		TrieNode[] children;
		boolean isWord;
		TrieNode(){
			children = new TrieNode[26];
			isWord = false;
		}
	}
	public void buildTrie(List<String> dict) {
		for(String word : dict) {
			TrieNode prev = root;
			for(char c : word.toCharArray()) {
				if(prev.children[c - 'a'] == null) {
					prev.children[c - 'a'] = new TrieNode();
				}
				prev = prev.children[c - 'a'];
			}
			prev.isWord = true;
		}
	}
	public ReplaceWordsTrie() {
		root = new TrieNode();
	}
	public String replaceWords(List<String> dict, String sentence) {
		String[] tokens = sentence.split("\\s+");
		StringBuilder result = new StringBuilder();
		for(String word : tokens) {
			result.append(getShortestWord(word));
			result.append(" ");
		}
		return result.substring(0,result.length()-1);
	}
	public String getShortestWord (String word) {
		TrieNode curr = root;
		StringBuilder s = new StringBuilder();
		for(char c : word.toCharArray()) {
			s.append(c);
			if(curr.children[c -'a']!=null) {
				if(curr.children[c-'a'].isWord) {
					return s.toString();
				}
				curr = curr.children[c -'a'];
			}else {
				// if the char is not present in trie
				return word;
			}
		}
		return word;
	}
	public static void main(String[] args) {
		ReplaceWordsTrie r = new ReplaceWordsTrie();
		List<String> dict = Arrays.asList("cat","rat","bat");
		r.buildTrie(dict);
		String sentence = "the cattle was rattled by the battery";
		System.out.println(r.replaceWords(dict, sentence));
	}

}
