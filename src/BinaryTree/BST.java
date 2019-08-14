package BinaryTree;

@SuppressWarnings("unchecked")
public class BST<E extends Comparable> extends BinaryTree<E> {

	public void add(E element) {
		if (root == null) {
			root = createNode(element, null);
			size++;
			return;
		}

		Node<E> temp = root;
		Node<E> parent = null;
		int cmp = 0;

		while (temp != null) {
			cmp = compare(element, temp.element);
			parent = temp;
			
			if (cmp > 0) {
				temp = temp.right;
			} else if (cmp < 0) {
				temp = temp.left;
			} else {
				temp.element = element;
				return;
			}
		}

		Node<E> newNode = createNode(element, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}

		afterAdd(newNode);
	}

	public void remove(E element) {
		remove(node(element));

	}

	public Boolean contains(E element) {
		return node(element) != null;
	}

	protected void afterAdd(Node<E> node) {}

	private void remove(Node<E> node) {
		if (node == null) { return; }
		size--;
		if (node.hasTwoChildren()) {//2
			Node<E> s = predecessor(node);
			node.element = s.element;
			node = s;
		}

		Node<E> replace =  node.left != null ? node.left : node.right;
		if (replace == null) {//leaf node
			if (node == root) {
				root = null;
			} else if (node == node.parent.left) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}

		} else {//1
			if (node == root) {
				root = replace;
				replace.parent = null;
			} else if (node == node.parent.left) {
				replace.parent = node.parent;
				node.parent.left = replace;
			} else {
				replace.parent = node.parent;
				node.parent.right = replace;
			}
		}
	} 

	private Node<E> node(E element) {
		Node<E> node = root;
		while (node != null) {
			int cmp = compare(element, node.element);
			if (cmp == 0) return node;
			if (cmp < 0 ) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return null;
	}
 
	private int compare(E e1, E e2) {
		return e1.compareTo(e2);
	}
}