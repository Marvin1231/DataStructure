package com.marving.ds.tree;

/**
 * Created by mercop on 2017/7/6.
 * 二叉树
 */
public class BinaryTree<T> implements Tree {

    protected BinaryNode<T> root = null;

    public BinaryTree(T value) {
        root = new BinaryNode<T>(value);
    }

    public BinaryTree(BinaryNode<T> root) {

        this.setRoot(root);
    }

    public BinaryNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinaryNode<T> root) {
        this.root = root;
    }

}
