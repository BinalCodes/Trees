import java.util.HashMap;

/*
 * Construct a binary tree from Inorder postorde traversals. 
 * Space complexity : O[N]
 * Time Complexity : O[N]
 * 
 */
public class ConstructBSTFromInPost {
	HashMap<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
	int pIndex;
	static class TreeNode{
		TreeNode left, right;
		int val;
		TreeNode(int v){
			this.val = v;
		}
	}
	public void buildInorderMap(int[] inorder) {
		for(int i=0 ;i <inorder.length ;i++) {
			inorderMap.put(inorder[i],i);
		}
	}
	public TreeNode buildTree(int[] inorder, int[] postorder, int start, int end) {
		if(start >end || pIndex <0)
			return null;
		int rootValue = postorder[pIndex];
		pIndex --;
		TreeNode t = new TreeNode(rootValue);
		if(start ==end)
			return t;
		int midIndex = inorderMap.get(rootValue);
		t.right = buildTree(inorder, postorder,  midIndex+1,  end);
		t.left = buildTree(inorder, postorder,  start,  midIndex-1);
		return t;
	}
	public void printPreorder(TreeNode t) {
		if(t == null)
			return;
		System.out.print(" " +t.val);
		printPreorder(t.left);
		printPreorder(t.right);
	}
	public void printInorder(TreeNode t) {
		if(t==null)
			return;
		printInorder(t.left);
		System.out.print(" "+t.val);
		printInorder(t.right);
	}
	public static void main(String[] args) {
		ConstructBSTFromInPost obj = new ConstructBSTFromInPost();
		int[] inorder = {9,3,15,20,7};
		int[] postorder = {9,15,7,20,3};
		obj.buildInorderMap(inorder);
		obj.pIndex = postorder.length-1;
		TreeNode t = obj.buildTree(inorder, postorder, 0, inorder.length-1);
		System.out.println("Preorder ");
		obj.printPreorder(t);
		System.out.println();
		System.out.println("Inorder ");
		obj.printInorder(t);
		}
	//Preorder  3 9 20 15 7
}
