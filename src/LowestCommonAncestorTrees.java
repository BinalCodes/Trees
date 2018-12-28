import java.util.ArrayList;

/**
 * @author binal
 * 
 *         1
 * 		/     \
 *     2		3
 *   /   \     /  \
 *   4	 5	   6   7
 *   LCA of 5 and 6 is 1
 *   LCA of 2 and 5 is 2
 *   LCA of 4 and 5 is 2
 */
public class LowestCommonAncestorTrees {
	 Node root;
	
	public static void main(String[] args) {
		LowestCommonAncestorTrees binarySearchTree = new LowestCommonAncestorTrees();
		binarySearchTree.root = new Node(10);
		binarySearchTree.root.left = new Node(8);
		binarySearchTree.root.right = new Node(20);
		binarySearchTree.root.left.left = new Node (6);
		binarySearchTree.root.left.right = new Node(19);
		binarySearchTree.root.right.left = new Node(18);
		binarySearchTree.root.right.right = new Node(30);
		
		int n1 = 30;
		int n2 = 18;
		System.out.println("the LCA of a binary search tree for n1= "+n1 +" and n2= " +n2 +" is "+lcaBST(binarySearchTree.root,n1,n2).data);
		
		LowestCommonAncestorTrees tree = new LowestCommonAncestorTrees();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node (64);
		tree.root.left.right = new Node(5);
		tree.root.right.left = new Node(6);
		tree.root.right.right = new Node(7);

		n1 = 6;
		n2 = 7;
		System.out.println("the LCA of a binary search tree for n1= "
				+ ""+n1 +" and n2= " +n2 +" is "+lcaBinaryTree(tree.root,n1,n2));
		
	}
	static class Node{
		int data;
		Node left,right;
		Node(int data){
			this.data = data;
			left = right = null;
		}
	}
	// for BST, the LCA would lie between n1 and n2
	public static Node lcaBST(Node root, int n1,int n2) {
		
		if (null== root)
			return null;
		
		if(root.data > n1 && root.data >n2)
			return lcaBST(root.left,n1,n2);
		if(root.data< n1 && root.data < n2)
			return lcaBST(root.right,n1,n2);
		return root;
		
			
	}
	// for BinaryTree LCA 
	public static int lcaBinaryTree(Node root, int n1,int n2) {
		ArrayList<Integer> path1 = new ArrayList<Integer>();
		ArrayList<Integer> path2 = new ArrayList<Integer>();
		
		if (!findPath(root, n1, path1) || !findPath(root, n2, path2)) {
            System.out.println((path1.size() > 0) ? "n1 is present" : "n1 is missing");
            System.out.println((path2.size() > 0) ? "n2 is present" : "n2 is missing");
            return -1;
        }
		int i =0;
		for (i =0; i< path1.size() && i< path2.size(); i++) {
			if(!path1.get(i).equals(path2.get(i))) {
				break;
			}
		}
		return path1.get(i-1);
		
	}
	public static boolean findPath(Node root,int n1,ArrayList<Integer> path) {
		if(root == null)
			return false;
		path.add(root.data);
		if(root.data == n1)
			return true;
		if(root.left!= null && findPath(root.left,n1,path))
			return true;
		if(root.right!= null && findPath(root.right,n1,path))
			return true;
		path.remove(path.size()-1);
		return false;
	}
}
