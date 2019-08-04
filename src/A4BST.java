import java.util.*;

/*
 * Complete the printInLevelOrder() method
 * Complete the visuallyIdentical(A4BST rhs) method
 * No other methods/variables should be added/modified
 */
public class A4BST<E extends Comparable<? super E>> {
	/*
	 * Grading:
	 * Correctly prints values in level order - 1.5pt
	 * Runs in O(N) - 1.5pt
	 */
	public String printInLevelOrder()
	{
		String content = "";
		Queue<Node> nodeQueue =new LinkedList<Node>();
		/*
		 * Add items from the tree to content one level at a time
		 * No line breaks are required between levels
		 * Ensure method runs in O(N) - does not revisit any node
		 */

		nodeQueue.add(root);

		while (nodeQueue.size() > 0) {
			Node current = nodeQueue.peek();
			content+= current.data + " ";
			nodeQueue.remove();
			if (current.left != null) {
				nodeQueue.add(current.left);
			}
			if (current.right != null) {
				nodeQueue.add(current.right);
			}
		}

		return content;
	}
	/*
	 * Grading:
	 * Correctly compares the structure of both trees - 3pts
	 */
	public boolean visuallyIdentical(A4BST rhs)
	{
		/*
		 * Check if the structure of the local tree and the rhs tree are identical
		 * This means they have the same left/right connections
		 * This means there are no extra connections in either tree
		 * The values at each position do not need to match, only the structure of the tree
		 * Think about if you drew both trees on paper, would they visually look the same (besides values)
		 */

		Queue<Node> thisQueue =new LinkedList<Node>();
		Queue<Node> rhsQueue =new LinkedList<Node>();

		thisQueue.add(root);
		rhsQueue.add(rhs.root);

		while (rhsQueue.size() > 0) {
			Node current = thisQueue.peek();
			Node compare = rhsQueue.peek();

			if (current.left != null && compare.left != null) {
				thisQueue.add(current.left);
				rhsQueue.add(compare.left);
			}else if (current.left != null || compare.left != null){
				return false;
			}
			if (current.right != null && compare.right != null) {
				thisQueue.add(current.right);
				rhsQueue.add(compare.right);
			}else if (current.right != null || compare.right != null) {
				return false;
			}

			thisQueue.remove();
			rhsQueue.remove();
		}

		return true;
	}
	
	private Node root;

	public A4BST()
	{
		root = null;
	}

	public String printTree()
	{
		return printTree(root);
	}
	private String printTree(Node current)
	{
		String content = "";
		if(current != null)
		{
			content += "Current:"+current.data.toString();
			if(current.left != null)
			{
				content += "; Left side:"+current.left.data.toString();
			}
			if(current.right != null)
			{
				content += "; Right side:"+current.right.data.toString();
			}
			content+="\n";
			content+=printTree(current.left);
			content+=printTree(current.right);

		}
		return content;
	}
	public String printInOrder()
	{
		return printInOrder(root);
	}
	private String printInOrder(Node current)
	{
		String content = "";
		if(current != null)
		{
			content += printInOrder(current.left);
			content += current.data.toString()+",";
			content += printInOrder(current.right);
		}
		return content;
	}
	public boolean contains(E val)
	{
		Node result = findNode(val, root);

		if(result != null)
			return true;
		else
			return false;
	}
	private Node findNode(E val, Node current)
	{
		//base cases
		if(current == null)
			return null;
		if(current.data.equals(val))
			return current;

		//recursive cases
		int result = current.data.compareTo(val);
		if(result < 0)
			return findNode(val, current.right);
		else
			return findNode(val, current.left);
	}
	public E findMin()
	{
		Node result = findMin(root);
		if(result == null)
			return null;
		else
			return result.data;
	}
	private Node findMin(Node current)//used in findMin and delete
	{
		while(current.left != null)
		{
			current = current.left;
		}
		return current;
	}
	public E findMax()
	{
		Node current = root;
		while(current.right != null)
		{
			current = current.right;
		}
		return current.data;
	}
	public void insert(E val)
	{
		root = insertHelper(val, root);
	}
	public Node insertHelper(E val, Node current)
	{
		if(current == null)
		{
			return new Node(val);
		}
		int result = current.data.compareTo(val);
		if(result < 0)
		{
			current.right = insertHelper(val, current.right);
		}
		else if(result > 0)
		{
			current.left = insertHelper(val, current.left);
		}
		else//update
		{
			current.data = val;
		}
		return current;
	}
	public void remove(E val)
	{
		root = removeHelper(val, root);
	}
	private Node removeHelper(E val, Node current)
	{
		if(current.data.equals(val))
		{
			if(current.left == null && current.right == null)//no children
			{
				return null;
			}
			else if(current.left != null && current.right != null)//two children
			{
				Node result = findMin(current.right);
				result.right = removeHelper(result.data, current.right);
				result.left = current.left;
				return result;
			}
			else//one child
			{
				return (current.left != null)? current.left : current.right;
			}
		}
		int result = current.data.compareTo(val);
		if(result < 0)
		{
			current.right = removeHelper(val, current.right);
		}
		else if(result > 0)
		{
			current.left = removeHelper(val, current.left);
		}
		return current;
	}


	private class Node
	{
		E data;
		Node left, right;
		public Node(E d)
		{
			data = d;
			left = null;
			right = null;
		}
	}

}
