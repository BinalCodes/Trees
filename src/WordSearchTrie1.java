import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WordSearchTrie1 {
	TrieNode root;
	public static void main(String[] args) {
		WordSearchTrie1 t = new WordSearchTrie1();
		List<String> words = new ArrayList(Arrays.asList("cats","dogs","cat","sand","and","dog"));
		String tosearch = "catsanddogs";
		//output should be list of list with [[cats,and,dogs],[cat,sand,dogs]]
		List<String> result = t.wordBreak(tosearch, words);
		for(String sentence : result) {
			System.out.println(sentence);
		}

	}
	static class TrieNode{
		TrieNode[] children;
		String word;
		boolean isWord;
		TrieNode(){
			children = new TrieNode[26];
		}
	}
	WordSearchTrie1() {
		root = new TrieNode();
	}
	public void buildTrie(List<String> words){
        for(String word : words){
            TrieNode curr = root;
            for(int i= 0; i <word.length(); i++){
                if(curr.children[word.charAt(i) - 'a'] == null){
                    curr.children[word.charAt(i) - 'a'] = new TrieNode();
                }
                curr = curr.children[word.charAt(i) - 'a'];
            }
            curr.word = word;
            curr.isWord = true;
        }
        //return root;
    }
	public ArrayList<List<String>> searchWords(String searchStr) {
		ArrayList<List<String>> result = new ArrayList<List<String>>();
		if(searchStr.length() == 0)
			return result;
		searchWordsUtil(result,searchStr);
		return result;
	}
	public void searchWordsUtil(ArrayList<List<String>> result, String searchStr) {
		TrieNode curr = root;
		List<String> list = new ArrayList<>();
		for(char c : searchStr.toCharArray()) {
			if(curr.children[c -'a'] == null)
				continue;
			if(curr.children[c-'a'].word !=null) {
				list.add(curr.children[c-'a'].word);
				curr= root;
			}
			else {
				curr = curr.children[c -'a'];
			}
		}
		result.add(list);
	}
	public List<String> wordBreak(String sentence, List<String> wordDict) {
        buildTrie(wordDict);
        HashMap<String, List<String>> dp = new HashMap<>();
        return DFS(sentence, 0, root, dp);
    }
    private List<String> DFS(String s, int index, TrieNode root, HashMap<String, List<String>> dp) {
        if (index == s.length()) {
            List<String> result = new ArrayList<>();
            result.add("");
            return result;
        }
        if (dp.containsKey(s.substring(index))) {
            return dp.get((s.substring(index)));
        }
        TrieNode forSearch = root;
        List<String> result = new ArrayList<>();
        for (int i = index; i < s.length(); i++) {
            forSearch = forSearch.children[s.charAt(i) - 'a'];
            if (forSearch == null) {
                break;
            } else if (forSearch.isWord) {
                List<String> tmp = DFS(s, i + 1, root, dp);
                String prefix = s.substring(index, i + 1);
                for (String t : tmp) {
                    result.add(t.length() != 0 ? prefix + " " + t : prefix);
                }
            }
        }
        dp.put(s.substring(index), result);
        return result;
    } 
}
