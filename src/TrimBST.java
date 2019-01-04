package myproject;

public class TrimBST {
    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        if (root.val >= L && root.val <= R) {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
        } else if (root.val < L) {
            root = trimBST(root.right, L, R);
        } else if (root.val > R) {
            root = trimBST(root.left, L, R);
        }
        return root;
    }
    public static void main (String[] args){
        TreeNode t = new TreeNode(3);
        TreeNode t1 = new TreeNode(0);
        TreeNode t2 = new TreeNode(4);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(1);TreeNode t5 = new TreeNode(5);
        t.left = t1;
        t.right = t2;
        t2.right = t5;
        t1.right = t3;
        t3.left = t4;
        TrimBST bst = new TrimBST();
        TreeNode result = bst.trimBST(t, 1,4);
        bst.printBST(result);
    }
    public void printBST(TreeNode root){
        if(root ==null) {
            return;
        }
        printBST(root.left);
        System.out.println(root.val);
        printBST(root.right);
    }
}
