import java.util.Scanner;


/*
*
* 编一个程序，读入用户输入的一串先序遍历字符串，根据此字符串建立一个二叉树（以指针方式存储）。
* 例如如下的先序遍历字符串： ABC##DE#G##F### 其中“#”表示的是空格，空格字符代表空树。
* 建立起此二叉树以后，再对二叉树进行中序遍历，输出遍历结果。
*
*输入包括1行字符串，长度不超过100。
*   可能有多组测试数据，对于每组数据，
    输出将输入字符串建立二叉树后中序遍历的序列，每个字符后面都有一个空格。
    每个输出结果占一行。
    *
    *
    *
    *
    * abc##de#g##f###（输入）
    * c b e g d f a （输出）
* */
public class TestDemo {
    static class TreeNode {
        public char val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(char val) {
            this.val = val;
        }
    }

    //二叉树的构建基于带空树的先序遍历结果来构建（ abc##de#g##f###（输入）也就是这个#）
    //如果只是给普通的先序遍历结果此时无法构建二叉树
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            //由于OJ上输入的数据可能是多组，一定要用循环的方式处理
            String line = scanner.next();
            TreeNode root = buildTree(line);
            //index = 0；
            //TreeNode root = createTreeProOrder（line）；
            //如果有这两行代码就可以不用写buildTree主要是输入的数据可能有多组要保证每次index=0
            inOrder(root);
            System.out.println();
        }
    }

    private static void inOrder(TreeNode root) {
        if (root == null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }



    private static int index = 0;
    //入口
    private static TreeNode buildTree(String line) {
        //输入数据可能存在多组，每次处理一组新的数据都需要从0开始重新计数
        index = 0;
        return createTreeProOrder(line);
    }

    //辅助递归的方法
    //每递归一次，就处理一个节点（从字符串中提取一个指点index下标的字符）
    private static TreeNode createTreeProOrder(String line) {
        char ch = line.charAt(index);
        if (ch == '#') {
            //当前这个是空树
            return null;
        }
        //如果节点非空，就可以访问这个节点，访问操作是创建节点
        TreeNode node = new TreeNode(ch);
        index++;
        node.left = createTreeProOrder(line);
        index++;
        node.right = createTreeProOrder(line);
        return node;
    }
}


