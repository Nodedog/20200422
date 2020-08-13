import java.util.ArrayList;
import java.util.List;


/*
* 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
*二叉树：[3,9,20,null,null,15,7],
*  3
   / \
  9  20
    /  \
   15   7
*
* 返回其层次遍历结果：

    [
      [3],
      [9,20],
      [15,7]
    ]
*
*
* */
public class TestDemo2 {

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //List里面套一个List 二维的 如上图
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root){
        if (root == null){
            return result;
        }
        result.clear();
        //对树进行先序遍历，递归时需要加上一个层数作为参数（层数直接从0开始算，方便与List下标对应）
        levelOrderHelper(root,0);
        return result;
    }


    //level是表示层数
    private void levelOrderHelper(TreeNode root, int level) {
        if (level == result.size()){
            result.add(new ArrayList<>());
        }
        List<Integer> curRow = result.get(level);

        //虽然题目要求返回的层序遍历的的节点值
        //但此处递归需要用先序遍历NLR
        curRow.add(root.val);
        if (root.left != null){
            levelOrderHelper(root.left,level+1);
        }
        if (root.right != null){
            levelOrderHelper(root.right,level+1);
        }
    }


}
