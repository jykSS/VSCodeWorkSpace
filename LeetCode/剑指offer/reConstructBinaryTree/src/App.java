/**
 * 题目描述
输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}
和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
示例1
输入
复制
[1,2,3,4,5,6,7],[3,2,4,1,6,5,7]
返回值
复制
{1,2,5,3,4,6,7}
 */
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length==0||in.length==0) {
            return null;
        }
        TreeNode treeNode = new TreeNode(pre[0]);
                
        return null;
    }
}
