package com.marving.ds.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import com.marving.util.callback.CallBack;

/**
 * Created by mercop on 2017/7/6.
 * 二叉树工具类
 */
public class BinaryUtils {

    /**
     * 获得二叉树节点个数
     *
     * @param root
     * @param <T>
     * @return
     */
    public static <T> int getSize(BinaryNode<T> root) {
        if (root == null || root.getData() == null)
            return 0;
        else
            return 1 + getSize(root.left) + getSize(root.right);
    }

    /**
     * 获得二叉树层级
     *
     * @param root
     * @param <T>
     * @return
     */
    public static <T> int getLevel(BinaryNode<T> root) {
        if (root == null || root.getData() == null)
            return 0;
        int left = getLevel(root.left);
        int right = getLevel(root.right);
        return left > right ? left + 1 : right + 1;
    }


    /**
     * 前序遍历二叉树
     *
     * @param root
     * @param <T>
     */
    public static <T> void traverseOfPre(BinaryNode<T> root) {
        if (root == null || root.getData() == null)
            return;
        System.out.print(root.getData() + " ");
        if (root.left != null)
            traverseOfPre(root.left);
        if (root.right != null)
            traverseOfPre(root.right);
    }

    /**
     * 中序遍历二叉树
     *
     * @param root
     * @param <T>
     */
    public static <T> void traverseOfIn(BinaryNode<T> root) {
        if (root == null || root.getData() == null)
            return;
        if (root.left != null)
            traverseOfIn(root.left);
        System.out.print(root.getData() + " ");
        if (root.right != null)
            traverseOfIn(root.right);
    }

    /**
     * 后续遍历
     *
     * @param root
     * @param <T>
     */
    public static <T> void traverseOfPost(BinaryNode<T> root) {
        if (root == null || root.getData() == null)
            return;
        if (root.left != null)
            traverseOfPost(root.left);
        if (root.right != null)
            traverseOfPost(root.right);
        System.out.print(root.getData() + " ");
    }

    /**
     * 带回调的前序遍历
     *
     * @param root
     * @param callback
     * @param <T>
     */
    public static <T> void traverseOfPre(BinaryNode<T> root, CallBack<T> callback) {
        if (root == null || root.getData() == null)
            return;
        callback.handleData(root.getData());
        if (root.left != null)
            traverseOfPre(root.left, callback);
        if (root.right != null)
            traverseOfPre(root.right, callback);
    }

    /**
     * 带回调的中序遍历
     *
     * @param root
     * @param callback
     * @param <T>
     */
    public static <T> void traverseOfIn(BinaryNode<T> root, CallBack<T> callback) {
        if (root == null || root.getData() == null)
            return;
        if (root.left != null)
            traverseOfIn(root.left, callback);
        callback.handleData(root.getData());
        if (root.right != null)
            traverseOfIn(root.right, callback);
    }

    /**
     * 带回调的后序遍历
     *
     * @param root
     * @param callback
     * @param <T>
     */
    public static <T> void traverseOfPost(BinaryNode<T> root, CallBack<T> callback) {
        if (root == null || root.getData() == null)
            return;
        if (root.left != null)
            traverseOfPost(root.left, callback);
        if (root.right != null)
            traverseOfPost(root.right, callback);
        callback.handleData(root.getData());
    }

    /**
     * 深度优先搜索
     *
     * @param root
     * @param k
     * @return
     */
    public static BinaryNode<Integer> deepFirstSearch(BinaryNode<Integer> root, Integer k) {

        if (k == null | root == null)
            return null;
        if (root.getData() == k)
            return root;
        Deque<BinaryNode<Integer>> deque = new LinkedList<BinaryNode<Integer>>();
        BinaryNode<Integer> node;
        deque.push(root);
        while (!deque.isEmpty()) {
            node = deque.pop();
            if (node.getData() == k)
                return node;
            if (node.hasRight()) {
                deque.push(node.right);
            }
            if (node.hasLeft())
                deque.push(node.left);
        }
        return null;
    }

    /**
     * 广度优先搜索
     *
     * @param root
     * @param k
     * @return
     */
    public static BinaryNode<Integer> broadFirstSearch(BinaryNode<Integer> root, Integer k) {

        if (k == null || root == null)
            return null;
        if (root.getData() == k)
            return root;
        Queue<BinaryNode<Integer>> queue = new LinkedList<BinaryNode<Integer>>();
        BinaryNode<Integer> node;
        queue.offer(root);
        while (queue.peek() != null) {

            node = queue.poll();
            if (node.getData() == k)
                return node;
            if (node.hasLeft()) {
                queue.offer(node.left);
            }
            if (node.hasRight()) {
                queue.offer(node.right);
            }
        }
        return null;
    }

    /**
     * 通过先序遍历和后序遍历创建二叉树
     *
     * @param pre
     * @param in
     * @return
     */
    public static BinaryNode<Integer> createTreeByPreAndIn(int[] pre, int[] in) {
        return createTreeByPreAndInRe(pre, in, 0, pre.length - 1, 0, in.length - 1);
    }


    private static BinaryNode<Integer> createTreeByPreAndInRe(int[] pre, int[] in, int pi, int pj, int ii, int ij) {
        if (pi > pj || ii > ij)
            return null;
        int rootNum = pre[pi];
        BinaryNode<Integer> node = new BinaryNode<>(rootNum);
        int mid = findNodeFromMid(in, rootNum, ii, ij);

        int leftLen = mid - ii;
        //int rightLen = ij - mid;
        node.left = createTreeByPreAndInRe(pre, in, pi + 1, pi + leftLen, ii, mid - 1);
        node.right = createTreeByPreAndInRe(pre, in, pi + leftLen + 1, pj, mid + 1, ij);

        return node;
    }

    private static int findNodeFromMid(int[] in, int value, int ii, int ij) {
        for (int i = ii; i <= ij; i++) {
            if (in[i] == value)
                return i;
        }
        return -1;

    }

    public static void main(String[] args) {
        Integer[] a = {1, 2, null, 3, 4};

        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
/*		BinaryTree<Integer> btree = TreeFactory.generateTree(a);
        BinaryUtils.traverseOfIn(btree.root);
		BinaryUtils.traverseOfPre(btree.root);
		System.out.println();
		System.out.println(BinaryUtils.getLevel(btree.root));
		System.out.println(BinaryUtils.getSize(btree.root));

		System.out.println(BinaryUtils.broadFirstSearch(btree.root, 2));
		System.out.println(BinaryUtils.deepFirstSearch(btree.root, 5));*/


        BinaryNode<Integer> node = createTreeByPreAndIn(pre, in);
        traverseOfPre(node);
        System.out.println();
        traverseOfPost(node);
        System.out.println();
        traverseOfIn(node);


    }
}
