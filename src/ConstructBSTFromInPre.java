import java.util.HashMap;

public class ConstructBSTFromInPre {
	HashMap<Integer,Integer> lookupMap = new HashMap<>();
	int pIndex = 0;
	static class TreeNode{
		TreeNode left, right;
		int val;
		TreeNode(int val){
			this.val = val;
		}
	}
	public TreeNode buildTree(int[] pre, int[] inorder, int inStart, int inEnd) {
		if(inStart >inEnd) {
			return null;
		}
		int val = pre[pIndex++];
		TreeNode t = new TreeNode(val);//always the root
		if(inStart == inEnd)
			return t;  
		int index = lookupMap.get(val);
		
		t.left = buildTree(pre,inorder,inStart,index-1);
		t.right = buildTree(pre,inorder,index+1,inEnd);
		return t;
	}
	public void buildInorderMap(int[] inorder) {
		for(int i=0 ;i <inorder.length ;i++) {
			lookupMap.put(inorder[i],i);
		}
	}
	public void printInorder(TreeNode t) {
		if(t==null)
			return;
		printInorder(t.left);
		System.out.println(" "+t.val);
		printInorder(t.right);
	}
	public static void main(String[] args) {
		int[] inorder = {1,2,3,4,5,6,7};
		int[] preorder = {4,2,1,3,6,5,7};
		ConstructBSTFromInPre obj = new ConstructBSTFromInPre();
		obj.buildInorderMap(inorder);
		obj.printInorder(obj.buildTree(preorder,inorder, 0, inorder.length-1));
	}

}
