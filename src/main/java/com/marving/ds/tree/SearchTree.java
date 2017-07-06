package com.marving.ds.tree;

import com.marving.util.callback.*;

/**
 * Created by mercop on 2017/7/6.
 * 二叉树查找树
 */
public class SearchTree extends BinaryTree<Integer> {

    public SearchTree(BinaryNode<Integer> root) {
        super(root);
    }

    /**
     * 插入节点
     * @param value
     */
    public void InsertNode(int value) {
        BinaryNode<Integer> node = new BinaryNode<Integer>(value);
        BinaryNode<Integer> cur = this.getRoot();
        BinaryNode<Integer> parent = this.getRoot();
        while (cur != null) {
            parent = cur;
            if (value < cur.getData())
                cur = cur.left;
            else
                cur = cur.right;
        }
        if (parent == null)
            root = node;
        else if (value < parent.getData())
            parent.left = node;
        else
            parent.right = node;
    }


    private BinaryNode<Integer> search(BinaryNode<Integer> x, Integer k) {
        if (k == null)
            return null;

        if (x == null || x.getData() == k)
            return x;
        if (k < x.getData())
            return search(x.left, k);
        else
            return search(x.right, k);
    }

    /**
     * 查找一个值是否在二叉树中
     * @param k
     * @return
     */
    public BinaryNode<Integer> search(Integer k) {
        return search(root, k);
    }

    /**
     * 非递归查找
     * @param k
     * @return
     */
    public BinaryNode<Integer> IterativeSearch(Integer k) {

        if (k == null)
            return null;
        BinaryNode<Integer> x = root;
        while (x != null && k != x.getData()) {
            if (k < x.getData())
                x = x.left;
            else
                x = x.right;
        }
        if (k == x.getData())
            return x;
        return null;
    }

    /**
     * 获得最大值
     * @return
     */
    public BinaryNode<Integer> getMaximum() {
        BinaryNode<Integer> x = root;
        while (x.right != null)
            x = x.right;
        return x;
    }

    /**
     * 获得最小值
     * @return
     */
    public BinaryNode<Integer> getMinimum() {
        BinaryNode<Integer> x = root;
        while (x.left != null)
            x = x.left;
        return x;
    }


    public static void main(String[] args) {
        Integer[] a = {11, 2, 3, 4};
        SearchTree btree = TreeFactory.generateSearchTree(a);
        btree.InsertNode(1);
        btree.InsertNode(5);
        BinaryUtils.traverseOfIn(btree.root);
        BinaryUtils.traverseOfPre(btree.root);
        BinaryUtils.traverseOfPost(btree.root);

        System.out.println();
        System.out.println(BinaryUtils.getLevel(btree.root));
        System.out.println(btree.search(5));
        System.out.println(btree.search(4));
        System.out.println(btree.search(6));


        GetMaxOfDatasC c;
        BinaryUtils.traverseOfIn(btree.root, new PrintDataC<Integer>());
        btree.InsertNode(20);

        BinaryUtils.traverseOfIn(btree.root, c = new GetMaxOfDatasC());
        System.out.println(c.getMax());
        System.out.println(btree.getMaximum());
    }

}
