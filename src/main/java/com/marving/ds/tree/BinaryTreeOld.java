package com.marving.ds.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mercop on 2017/7/6.
 * 二叉树 - old
 */
public class BinaryTreeOld implements Tree{
	
	public String value;
	public BinaryTreeOld left;
	public BinaryTreeOld right;
	
	public BinaryTreeOld() {
	}

	public BinaryTreeOld(String value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}

	public static BinaryTreeOld generateTree(String[] nodes){
		List<BinaryTreeOld> nodeList = new LinkedList<BinaryTreeOld>();
		if(nodes==null||nodes.length<=0){
			return null;
		}
		for(int i = 0; i < nodes.length;i++){
			BinaryTreeOld node  = new BinaryTreeOld();
			if(nodes[i] != null){
				node.value = nodes[i];
			}
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
		if(nodes.length%2 == 1){
			nodeList.get(lastParentIndex).right = nodeList.get(lastParentIndex*2+2);
		}
		
		return nodeList.get(0);
	}
	

	public static BinaryTreeOld generateTree2(String[] nodes){
		List<BinaryTreeOld> nodeList = new LinkedList<BinaryTreeOld>();
		if(nodes==null||nodes.length<=0){
			return null;
		}
		for(int i = 0; i < nodes.length;i++){
			BinaryTreeOld node  = new BinaryTreeOld();
			if(nodes[i] != null){
				node.value = nodes[i];
			}
			nodeList.add(node);
		}
		int countOfNullNode = 0;
		for(int i = 0; i < nodes.length/2-1;i++){
			
			if(nodeList.get(i) ==null){
				countOfNullNode++;
				continue;
			}
			//Left child
			if((i-countOfNullNode)*2+1 < nodes.length){
				nodeList.get(i).left = nodeList.get(i*2+1);
			}
			//Right child
			if((i-countOfNullNode)*2+2 < nodes.length){
				nodeList.get(i).right = nodeList.get(i*2+2);	
			}
		}
		int lastParentIndex = nodes.length/2-1;
		System.out.println("last" + lastParentIndex);
		nodeList.get(lastParentIndex).left = nodeList.get((lastParentIndex-countOfNullNode)*2+1);
		if(nodes.length%2 == 0){
			nodeList.get(lastParentIndex).right = nodeList.get((lastParentIndex-countOfNullNode)*2+2);
		}
		return nodeList.get(0);
	}

	public static void traverseOfPre(BinaryTreeOld root){
		if(root == null || root.value == null)
			return;
		System.out.print(root.value +" ");
		if(root.left!=null)
			traverseOfPre(root.left);
		if(root.right!=null)
			traverseOfPre(root.right);
	}
}
