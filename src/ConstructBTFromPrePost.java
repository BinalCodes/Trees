import java.util.HashMap;
/*
 * Construct Binary Tree from Preorder and postorder
 * 
 * TC : O[N]
 * SC: O[N]
 * 
 * Explanation of logic :
 * Let us consider the two given arrays as pre[] = {1, 2, 4, 8, 9, 5, 3, 6, 7} and post[] = {8, 9, 4, 5, 2, 6, 7, 3, 1};
In pre[], the leftmost element is root of tree. Since the tree is full and array size is more than 1. 
The value next to 1 in pre[], must be left child of root. So we know 1 is root and 2 is left child. 
How to find the all nodes in left subtree? We know 2 is root of all nodes in left subtree. 
All nodes before 2 in post[] must be in left subtree. Now we know 1 is root, 
elements {8, 9, 4, 5, 2} are in left subtree, and the elements {6, 7, 3} are in right subtree.
 */


public class ConstructBTFromPrePost {
	HashMap<Integer, Integer> postOrderMap = new HashMap<Integer, Integer>();
	int pIndex;
	static class TreeNode{
		TreeNode left, right;
		int val;
		TreeNode(int v){
			this.val = v;
		}
	}
	public void buildPostorderMap(int[] postorder) {
		for(int i=0 ;i <postorder.length ;i++) {
			postOrderMap.put(postorder[i],i);
		}
	}
	public TreeNode constructTree(int[] pre, int[] post, int start, int end) {
		if(pIndex >=pre.length || start>end)
			return null;
		int rootValue = pre[pIndex];
		pIndex++;
		TreeNode t = new TreeNode(rootValue);
		if(pIndex >= pre.length || start ==end)
			return t;
		int midIndex = postOrderMap.get(pre[pIndex]);
		if(midIndex <=end) {
			t.left = constructTree(pre,post,start,midIndex);
			t.right = constructTree(pre,post,midIndex+1 , end);
		}
		return t;
	}
	public void printInorder(TreeNode t) {
		if(t==null)
			return;
		printInorder(t.left);
		System.out.print(" "+t.val);
		printInorder(t.right);
	}
	public static void main(String[] args) {
		int pre[] = { 1, 2, 4, 8, 9, 5, 3, 6, 7 }; 
        int post[] = { 8, 9, 4, 5, 2, 6, 7, 3, 1 }; 
        
        ConstructBTFromPrePost obj = new ConstructBTFromPrePost();
        obj.buildPostorderMap(post);
        obj.pIndex = 0;
        int size = pre.length;
        TreeNode root = obj.constructTree(pre, post, 0, size-1); 
        System.out.println("Inorder ");
        obj.printInorder(root);
	}
//Inorder traversal of the constructed tree: 8 4 9 2 5 1 6 3 7
}
