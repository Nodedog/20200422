

/*
* 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，
* 只能调整树中结点指针的指向。
* */



//二叉搜索树的特点：中序遍历的结果结果为有序序列

public class TestDemo4 {
    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(char val) {
            this.val = val;
        }
    }

    //返回值的含义表示链表的头结点
    public TreeNode Convert(TreeNode root) {
        if (root == null){
            return null;
        }
        if (root.left == null && root.right == null){
            //只有一个根节点 得到的链表也就只有一个根节点
            return root;
        }

        //这里递归要用中序遍历


        //这里面left相当于链表中的prve，riht相当于链表中的next

        //先递归左子树
        //这个方法一执行就把这棵树的左子树完整转换为双向链表
        //left表示链表的头结点
        TreeNode left = Convert(root.left);



        //再处理根节点
        //此时需要用尾插 定义一个leftTail等于链表头部left然后循环 将其置为链表尾部为后面连接根节点准备
        TreeNode leftTail = left;
        while (leftTail != null && leftTail.right != null){
            leftTail = leftTail.right;
        }
        //上面循环结束 就说明leftTail就是left链表的最后一个节点
        //这里if的判断语句是防止left为空 然后leftTail也是null
        if (leftTail != null){
            //这里是连接左子树链表和 根节点链表的头部
            leftTail.right = root;
            root.left = leftTail;
        }



        //最后处理右子树,先定义一个right表示右子树链表的头部
        TreeNode right = Convert(root.right);
        //判断一下右子树是否为空，不为空则 链接头结点的链表和右子树的链表
        if (right != null){
            right.left = root;
            root.right = right;
        }


        //这个方法返回的是链表的头节点 这里判断left为空不，为空就返回root
        return left != null ? left : root;
    }
}
