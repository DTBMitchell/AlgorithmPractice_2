import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * Complete the populateFromPostfix(String equation) method
 * Complete the populateFromPrefix(String equation) method
 * f(N) and O(N) for populateFromInfix(String equation) method
 * No other methods/variables should be added/modified
 */
public class A4EquationTree {
	/*
	 * Grading:
	 * Correctly fills in tree values from equation string - 3pts
	 * f(N) formula (show your work) - 0.5pt
	 * O(N) reduction - 0.5pt
	 */
	public void populateFromPostfix(String equation) //f(N) = 12N + 5
	{
		/*
		 * Given a postfix string, create a series of nodes that represent the formula
		 */
		Stack<Node> st = new Stack(); //op = 1
		Node current; //op = 1

		for (int i = 0; i < equation.length(); i++) {//op = 7N

			if (equation.charAt(i) != '+' && equation.charAt(i) != '-' &&
					equation.charAt(i) != '*' && equation.charAt(i) != '/' ) { // op = 6

				current = new Node(equation.substring(i,i+1));
				st.push(current);

			} else { //op = 4
				current = new Node(equation.substring(i,i+1));

				current.right = st.pop();
				current.left= st.pop();

				st.push(current);
			}
			//op += 3 for i++
		}

		root = st.pop();//op = 1
	}
	/*
	 * Grading:
	 * Correctly fills in tree values from equation string - 3pts
	 * f(N) formula (show your work) - 0.5pt
	 * O(N) reduction - 0.5pt
	 */
	public void populateFromPrefix(String equation) //f(N) = 12N + 5
	{
		/*
		 * Given a prefix string, create a series of nodes that represent the formula
		 */

		Stack<Node> st = new Stack<>(); //op = 1
		Node top; //op = 1

		//op = 2
		for (int i = equation.length()-1; i >=0; i--) {//op = 7N
			if (equation.charAt(i) != '+' && equation.charAt(i) != '-' &&
					equation.charAt(i) != '*' && equation.charAt(i) != '/' ) { //op = 6

				top = new Node(equation.substring(i,i+1));
				st.push(top);
			}else{ //op = 4
				top = new Node(equation.substring(i,i+1));

				top.left = st.pop();
				top.right = st.pop();

				st.push(top);
			}

			//op += 3 for i--
		}

		root = st.pop(); //op = 1
	}
	/*
	 * Grading:
	 * f(N) formula (show your work) - 1pt
	 * O(N) reduction - 1pt
	 * Give best and average case reduction for BONUS - 1pt
	 */
	public void populateFromInfix(String equation)
	{
		root = populateFromInfixHelper(equation);
	}
	public Node populateFromInfixHelper(String equation)//8N + 11 //O(N) = N
	{
		if(equation.length() == 1) // op = 1
		{
			return new Node(equation);//math operand // op = 1
		}

		String temp = equation.substring(1, equation.length()-1);//remove wrapper paren // op = 1

		//begin search for middle of equation
		int parenCount = 0;
		int mid = 0;
		// op = 4 for the last two lines and for loop setup
		for(int i = 0; i < temp.length(); i++) // op = 8N
		{
			if(temp.charAt(i) == '(')//op = 1
				parenCount++;// op = 2
			if(temp.charAt(i) == ')') //op = 1
				parenCount--; //op = 2
			if(parenCount == 0) // op = 1
			{
				mid = i+1; // op = 2
				break;
			}
			//op += 3 for loop
		}
		//middle
		Node n = new Node(""+temp.charAt(mid)); // op = 1
		//first half
		n.left = populateFromInfixHelper(temp.substring(0, mid));//recursive // op = 1
		//second half
		n.right = populateFromInfixHelper(temp.substring(mid+1));//recursive // op = 1

		//op = 1
		return n;
	}
	
	private Node root;
	public A4EquationTree()
	{
		root = null;
	}

	//(left parent right)
	//(->left->parent->right->)
	public String printInfix()
	{
		return printInfixHelper(root);
	}
	private String printInfixHelper(Node n)//O(N) - visit each node only once
	{
		String content = "";
		if(n != null && n.left != null)
		{
			content += "(";
			content += printInfixHelper(n.left);//left side
			content += n.value;//middle item//parent
			content += printInfixHelper(n.right);//right side
			content += ")";
		}
		else if(n != null)
		{
			content += n.value;//middle item//parent
		}
		return content;
	}
	//left -> right -> parent
	public String printPostfix()
	{
		return printPostfixHelper(root);
	}
	private String printPostfixHelper(Node n)
	{
		String content = "";
		if(n != null && n.left != null)
		{
			content += printPostfixHelper(n.left);//left side
			content += printPostfixHelper(n.right);//right side
			content += n.value;//middle item//parent
		}
		else if(n != null)
		{
			content += n.value;//middle item//parent
		}
		return content;
	}
	//parent -> left -> right
	public String printPrefix()
	{
		return printPrefixHelper(root);
	}
	private String printPrefixHelper(Node n)
	{
		String content = "";
		if(n != null && n.left != null)
		{
			content += n.value;//middle item//parent
			content += printPrefixHelper(n.left);//left side
			content += printPrefixHelper(n.right);//right side
		}
		else if(n != null)
		{
			content += n.value;//middle item//parent
		}
		return content;
	}

	private class Node
	{
		String value;
		Node left, right;
		public Node(String v)
		{
			value = v;
			left = null;
			right = null;
		}
	}
}
