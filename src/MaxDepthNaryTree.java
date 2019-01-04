import java.util.List;

public class MaxDepthNaryTree {
    public int maxDepth(TreeNode root){
        // since we dont have depth in this method definition
        if(root!=null)
            return helper(root,1);
        return 0;
    }
    public int helper (TreeNode root, int depth){
        if(root == null){
            return 0;
        }
        //int depth = 0;
        return depth;
    }
}
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
