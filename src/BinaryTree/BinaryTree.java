package BinaryTree;

import Printer.BinaryTreeInfo;

import java.util.*;
public class BinaryTree<E> implements BinaryTreeInfo {
	public Node<E> root;
	protected int size;

	public void preorderTraversal() {
		preorderTraversal(root);
	}

	public void inorderTraversal() {
		inorderTraversal(root);
	}

	public void postorderTraversal() {
		postorderTraversal(root);
	}

	public void levelorderTraversal() {
		levelorderTraversal(root);
	}

	public void invert() {
		invert(root);
	}

	public void clear() {
		root = null;
		size = 0;
	}

	public Node<E> successor(Node<E> node) {
		//has right child, the successor is the node which has the minimun element in the right child tree
		if (node.right != null) {
			Node<E> child = node.right;
			while (child.left != null) {
				child = child.left;
			}
			return child;
		}

		Node<E> tmp = node;
		while (tmp.parent != null && tmp.parent.right == tmp) {
			tmp = tmp.parent;
		}
		return tmp.parent;
	}

	public Node<E> predecessor(Node<E> node) {
		//has left child, the Predecessor is the node which has the maximum element in the left child tree
		if (node.left != null) {
			Node<E> child = node.left;
			while (child.right != null) {
				child = child.right;
			}
			return child;
		}

		//No left child, then no parent, so no Predecessor


		//No left child, the Predecessor is first parent which less than it self
		Node<E> tmp = node;
		while (tmp.parent != null && tmp.parent.left == tmp) {//when parent == null or node is the right child of its parent then break
			tmp = tmp.parent;
		}
		return tmp.parent;
	}

	protected Node<E> createNode(E element, Node<E> parent) {
		return new Node<E>(element, parent);
	}

	private void preorderTraversal(Node<E> node) {
		if (node == null) return;
		System.out.println(node.element);
		preorderTraversal(node.left);
		preorderTraversal(node.right);
	}


	private void inorderTraversal(Node<E> node) {
		if (node == null) return;
		inorderTraversal(node.left);
		System.out.println(node.element);
		inorderTraversal(node.right);
	}

	private void postorderTraversal(Node<E> node) {
		if (node == null) return;
		postorderTraversal(node.left);
		postorderTraversal(node.right);
		System.out.println(node.element);
	}

	private void levelorderTraversal(Node<E> node) {
		if (node == null) return;
		Queue<Node<E>> queue = new LinkedList<Node<E>>();
		queue.offer(node);
		while (!queue.isEmpty()) {
			Node<E> head = queue.poll();
			System.out.println(head.element);
			if (head.left != null) queue.offer(head.left);
			if (head.right != null) queue.offer(head.right);

		}
	}

	private void invert(Node<E> node) {
		if (node == null) return;

		invert(node.left);

		Node<E> temp = node.left;
		node.left = node.right;
		node.right = temp;

		invert(node.right);
	}

	@Override
	public Object root() {
		return root;
	}

	@Override
	public Object left(Object node) {
		return ((Node<E>)node).left;
	}

	@Override
	public Object right(Object node) {
		return ((Node<E>)node).right;
	}

	@Override
	public Object string(Object node) {
		return ((Node<E>)node).element;
	}

	public static class Node<E> {
		public E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;

		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}

		public Boolean hasTwoChildren() {
			return left != null && right != null;
		}

		public boolean isLeftChild() {
			return (parent != null) && (parent.left == this);
		}

		public boolean isRightChild() {
			return (parent != null) && (parent.right == this);
		}
	}
}