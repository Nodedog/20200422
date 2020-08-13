/*
*
*
* 根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7


* */

public class TestDemo5 {

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //preorder和inorder这两个数组的长度肯定相同
    //为了辅助遍历定义一个index搞清楚preorder访问到哪个元素
    private int index = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        index = 0;
        return buildTreeHelper(preorder,inorder,0,inorder.length);
    }

    //[inorderLeft,inorderRight)表示当前子树中序遍历的区间
    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int inorderLeft, int inorderRight) {
        if (inorderLeft >= inorderRight){
            return  null;
        }
        if (index >= preorder.length){
            return null;
        }
        //根据index取出当前树的根节点值，并构造根节点
        TreeNode newNode = new TreeNode(preorder[index]);
        //知道根节点之后，还需要根据根节点在中序遍历中确定左右子树范围

        int pos = find(inorder,inorderLeft,inorderRight,newNode.val);
        index++;
        //用find找到pos位置之后就可以确定左右子树的范围
        //下面例子中3是根节点 对应的pos=1， 3左边对应下标[0,1)这是左子树在中序遍历的区间
        //3右边对应下标[2,5)这是右子树在中序遍历的区间
        //例：前序遍历 preorder = [3,9,20,15,7]  中序遍历 inorder = [9,3,15,20,7]
        //左子树对应中序遍历的下标区间：[inorderleft，pos）
        //右子树对应中序遍历的下标区间：[pos+1,inorderright)
        newNode.left = buildTreeHelper(preorder,inorder,inorderLeft,pos);
        newNode.right = buildTreeHelper(preorder,inorder,pos+1,inorderRight);
        return newNode;
    }

    //用find这个方法当前节点在中序遍历中的位置
    private int find(int[] inorder, int inorderLeft, int inorderRight, int val) {
        for (int i = inorderLeft; i <inorderRight ; i++) {
            if (inorder[i] == val){
                return i;
            }
        }
        return -1;
    }
}
