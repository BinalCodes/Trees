import java.util.LinkedList;
import java.util.Queue;

public class MaxDepthBinaryTree {
    public static void main(String[] args) {

        TreeNode child = new TreeNode(1);
        TreeNode childRight = new TreeNode(4);
        TreeNode leftChild = new TreeNode(2,child,childRight);
        TreeNode rightChild = new TreeNode(13);
        TreeNode root = new TreeNode(10,leftChild,rightChild);
        MaxDepthBinaryTree b = new MaxDepthBinaryTree();
        System.out.println("Max Depth of Binary tree is "+b.findMaxDepth(root));
        System.out.println("Max Depth of Binary tree using iteration is "+b.findMaxDepthIteration(root));
    }

    public  int findMaxDepth (TreeNode root){
        if(root ==null){
            return 0;
        }
        int height_left = findMaxDepth(root.left);
        int height_right = findMaxDepth(root.right);
        return 1+ Math.max(height_left,height_right);
    }

    public int findMaxDepthIteration(TreeNode root){
        if (root == null)
            return 0;
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        if (root!= null){
            stack.add(root);
        }
        int depth = 0;
        while(!stack.isEmpty()){
            TreeNode currentNode = stack.pollLast();
            if(currentNode!=null){
                depth++;
                stack.add(currentNode.left);
                stack.add(currentNode.right);
            }
        }
        return depth;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    TreeNode(int x) {
        val = x;
    }
    TreeNode(int x, TreeNode left, TreeNode right) {
        val = x;
        this.left = left;
        this.right = right;
    }
}
