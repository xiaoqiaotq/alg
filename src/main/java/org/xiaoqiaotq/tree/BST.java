package org.xiaoqiaotq.tree;

import java.util.LinkedList;
import java.util.Random;

/**
 * binary search tree
 * @see <a href="http://https://en.wikipedia.org/wiki/Binary_search_tree">WIKI BST</a>
 *
 */
public class BST<T extends Comparable<T>> {
//    static int[] arr = new int[]{1333,3,55,3223,13,13,1,2112,31231,12,1,4,43,355,2,23,414};
    static int[] arr = new Random().ints(100,1,100).toArray();
    static int[] randoms = new Random().ints(10,1,100).toArray();
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        TreeNode<Integer> root = null;
        //构造BST
        for (int i = 0; i < arr.length; i++) {
            root = bst.insert(root, arr[i]);
        }
        //遍历打印
        bst.inOrder(root);
        //验证存在
//        for (int i = 0; i < randoms.length; i++) {
//            boolean exists = bst.exists(root, randoms[i]);
//            System.err.println(randoms[i] + " " + exists);
//        }
        //range query
        bst.rangeQuery(root, 80, 100);
        System.err.println("================");
//        bst.levelOrder(root);
        bst.constructTreeInLevelOrder();

    }
    //插入
    public TreeNode insert(TreeNode<T> root,T key) {
        if (root == null) {
            TreeNode treeNode = new TreeNode(key,null);
            root = treeNode;
            return root;
        }
        if (key.compareTo(root.getKey()) > 0) {
            if (root.getRight() == null) {
                TreeNode treeNode = new TreeNode(key,null);
                root.setRight(treeNode);
            }else{
                insert(root.getRight(), key);
            }
        }else{
            if (root.getLeft() == null) {
                TreeNode treeNode = new TreeNode(key,null);
                root.setLeft(treeNode);
            }else{
                insert(root.getLeft(), key);
            }
        }
        return root;
    }

    //中序遍历 O(n)
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.getLeft());
        System.err.println(root.getKey());
        inOrder(root.getRight());
    }
    //存在否 O(n)
    public boolean exists(TreeNode<T> root ,T key ) {
        if (root == null) {
            return false;
        }
        if (root.getKey().compareTo(key) == 0) {
            return true;
        }else if(root.getKey().compareTo(key) > 0){
            return exists(root.getLeft(), key);
        }else{
            return exists(root.getRight(), key);
        }
    }
    //范围查询
    //还是中序遍历 复杂度O(log(N)+ M)   M=(max-min)
    public void rangeQuery(TreeNode<T> root ,T min,T max){
        if (root == null) {
            return;
        }
        if( min.compareTo(root.getKey()) < 0){
            rangeQuery(root.getLeft(), min,max);
        }
        if (min.compareTo(root.getKey()) <= 0 && max.compareTo(root.getKey()) >= 0) {
            System.err.print(root.getKey()+", ");
        }
        if( max.compareTo(root.getKey()) > 0){
            rangeQuery(root.getRight(), min,max);
        }

    }

    //Breadth First or Level Order Traversal
    public void levelOrder(TreeNode<T> root) {
        LinkedList<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            System.err.println(temp.getKey());
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
    }


    //Breadth First or Level Order Traversal
    public void constructTreeInLevelOrder() {
        int[] arrs = {0, 1, 3, 4, 5, 6};
        TreeNode<T> root = null;
        LinkedList<TreeNode<T>> queue = new LinkedList<>();
        for (int i = 0; i < arrs.length; i++) {
            TreeNode treeNode = new TreeNode(arrs[i], null);
            queue.add(treeNode);
            TreeNode<T> parent = queue.peek();
            if (root == null) {
                root = treeNode;
            }else if(parent.left==null){
                parent.left = treeNode;
            }else if(parent.right==null){
                parent.right = treeNode;
                queue.poll();
            }
        }
        System.err.println("============level order===========");
        levelOrder(root);
        System.err.println("============in order===========");
        inOrder(root);

    }
    //比较复杂，情况比较多
    public void remove(TreeNode<T> root ,T key){

    }

    static class TreeNode<T>{
       private TreeNode<T> left;
       private TreeNode<T> right;
       private T key;
       private T value;

        public TreeNode(T key, T value) {
            this.key = key;
            this.value = value;
        }

        public TreeNode() {
        }

        public TreeNode<T> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<T> left) {
            this.left = left;
        }

        public TreeNode<T> getRight() {
            return right;
        }

        public void setRight(TreeNode<T> right) {
            this.right = right;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }


}
