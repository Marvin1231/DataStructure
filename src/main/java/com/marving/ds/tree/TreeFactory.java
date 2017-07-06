package com.marving.ds.tree;

import java.util.LinkedList;
import java.util.List;

public class TreeFactory {

	/**
	 * 通过数组创建一课二叉树
	 * @param nodes
	 * @param <T>
	 * @return
	 */
	public static<T> BinaryTree<T> generateTree(T[] nodes){
		List<BinaryNode<T>> nodeList = new LinkedList<BinaryNode<T>>();
		BinaryNode<T> node;
		if(nodes==null||nodes.length<=0){
			return null;
		}
		for(int i = 0; i < nodes.length;i++){
			if(nodes[i] == null)
				node = null;
			else
				node  = new BinaryNode<T>(nodes[i]);
			nodeList.add(node);
		}
		for(int i = 0; i < nodes.length/2-1;i++){
			
			if(nodeList.get(i) ==null)
				continue;
			//Left child
			if(i*2+1 < nodes.length){
				nodeList.get(i).left = nodeList.get(i*2+1);
			}
			//Right child
			if(i*2+2 < nodes.length){
				nodeList.get(i).right = nodeList.get(i*2+2);	
			}			
		}
		int lastParentIndex = nodes.length/2-1;
		nodeList.get(lastParentIndex).left = nodeList.get(lastParentIndex*2+1);
		//�ڵ�����Ϊ����,��󸸽ڵ�����ҽڵ�
		if(nodes.length%2 == 1){
			nodeList.get(lastParentIndex).right = nodeList.get(lastParentIndex*2+2);
		}
		BinaryTree<T> bTree = new BinaryTree<T>(nodeList.get(0));
		return bTree;
	}

	/**
	 * 通过数组创建一棵二叉搜索树
	 * @param nodes
	 * @return
	 */
	public static SearchTree generateSearchTree(Integer[] nodes){
		
		if(nodes==null||nodes.length<=0){
			return null;
		}
		BinaryNode<Integer> root  = new BinaryNode<Integer>(nodes[0]);
		SearchTree bTree = new SearchTree(root);
		for(int i = 1; i < nodes.length;i++){
			InsertNode(bTree,nodes[i]);
		}
		return bTree;
	}

	/**
	 * 给一棵二叉搜索树插入一个节点
	 * @param bTree
	 * @param value
	 */
	public static void InsertNode(SearchTree bTree,Integer value){
		if(value == null){
			return;
		}
		BinaryNode<Integer> node = new BinaryNode<Integer>(value);
		BinaryNode<Integer> cur = bTree.getRoot();
		BinaryNode<Integer> parent = bTree.getRoot();
		while(cur != null){
			parent = cur;
			if(value < cur.getData())
				cur = cur.left;
			else
				cur = cur.right;			
		}
		if(parent == null)
			bTree.setRoot(node);
		else if(value < parent.getData())
			parent.left = node;
		else 
			parent.right = node;
	}
}
