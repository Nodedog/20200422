public class TestDemo6 {

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private StringBuilder stringBuilder = new StringBuilder();
    public String tree2str(TreeNode t) {
        if (t == null){
            return "";
        }
        halper(t);
        //把最外层的一对括号删除
        stringBuilder.deleteCharAt(0);
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    private void halper(TreeNode root) {
        if (root == null){
            return;
        }
        stringBuilder.append("(");
        stringBuilder.append(root.val);
        halper(root.left);
        if (root.left == null && root.right != null){
            stringBuilder.append("()");
        }
        halper(root.right);
        stringBuilder.append(")");
    }


}
