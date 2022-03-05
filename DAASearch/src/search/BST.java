package search;

import java.util.ArrayList;

class Node {
	 
    int key;
    Node left, right;
 
    Node(int keyItem)
    {
        key = keyItem;
        left = right = null;
    }
}
 
class Index {
 
    int index = 0;
}

public class BST {

	Index index = new Index();
	Node root;
    Node constructTreeUtil(ArrayList<Integer> pre, Index preIndex,
                           int low, int high, int size)
    {
        if (preIndex.index >= size || low > high) {
            return null;
        }
        Node root = new Node(pre.get(preIndex.index));
        preIndex.index = preIndex.index + 1;
        if (low == high) {
            return root;
        }
        int i;
        for (i = low; i <= high; ++i) {
            if (pre.get(i) > root.key) {
                break;
            }
        }
        root.left = constructTreeUtil(
            pre, preIndex, preIndex.index, i - 1, size);
        root.right = constructTreeUtil(pre, preIndex, i,
                                       high, size);
 
        return root;
    }
 
    Node constructTree(ArrayList<Integer> pre, int size)
    {
        return constructTreeUtil(pre, index, 0, size - 1,
                                 size);
    }
 
    void printInorder(Node node)
    {
        if (node == null) {
            return;
        }
        printInorder(node.left);
        System.out.print(node.key + " ");
        printInorder(node.right);
    }
    
    void printPreorder(Node node)
    {
        if (node == null) {
            return;
        }
        System.out.print(node.key + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }
    
    void printPostorder(Node node)
    {
        if (node == null) {
            return;
        }
        printPostorder(node.left);
        printPostorder(node.right);
        System.out.print(node.key + " ");
    }
    
    public Node search(Node root, int key)
    {
        if (root==null || root.key==key)
            return root;
        if (root.key < key)
           return search(root.right, key);
        return search(root.left, key);
    }
    
    public int searchReturn(ArrayList<Integer> inputData, int searchElement) {
    	Node finalTree = constructTree(inputData, inputData.size());
    	Node searchEle = search(finalTree, searchElement);
    	if(searchEle == null) {
    		return -1;
    	}else {
    		return searchEle.key;
    	}
		//printInorder(searchEle);
    }
	
}
