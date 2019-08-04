
public class A4Driver {

	public static void main(String[] args) {
		A4BST<Integer> tree1 = new A4BST<>();
		tree1.insert(5);
		tree1.insert(3);
		tree1.insert(1);
		tree1.insert(2);
		tree1.insert(9);
		tree1.insert(10);
		tree1.insert(25);
		A4BST<Integer> tree2 = new A4BST<>();
		tree2.insert(8);
		tree2.insert(5);
		tree2.insert(1);
		tree2.insert(3);
		tree2.insert(15);
		tree2.insert(20);
		tree2.insert(25);
		A4BST<Integer> tree3 = new A4BST<>();
		tree3.insert(1);
		tree3.insert(2);
		tree3.insert(3);
		tree3.insert(5);
		tree3.insert(9);
		tree3.insert(10);
		tree3.insert(25);
		System.out.println(tree1.printInLevelOrder());//5, 3, 9, 1, 10, 2, 25
		System.out.println(tree2.printInLevelOrder());//8, 5, 15, 1, 20, 3, 25
		System.out.println(tree3.printInLevelOrder());//1, 2, 3, 4, 5, 9, 10, 25
		System.out.println(tree1.visuallyIdentical(tree2));//true
		System.out.println(tree1.visuallyIdentical(tree3));//false
	
		String infix = "((a+(b*c))+(((d*e)+f)*g))";
		String postfix = "abc*+de*f+g*+";
		String prefix = "++a*bc*+*defg";
		
		System.out.println("Original infix:"+infix);
		A4EquationTree eq1 = new A4EquationTree();
		eq1.populateFromInfix(infix);
		System.out.println(eq1.printInfix());
		System.out.println(eq1.printPostfix());
		System.out.println(eq1.printPrefix());

		System.out.println();
		
		System.out.println("Original postfix:"+postfix);
		A4EquationTree eq2 = new A4EquationTree();
		eq2.populateFromPostfix(postfix);
		System.out.println(eq2.printInfix());
		System.out.println(eq2.printPostfix());
		System.out.println(eq2.printPrefix());

		System.out.println();

		System.out.println("Original prefix:"+prefix);
		A4EquationTree eq3 = new A4EquationTree();
		eq3.populateFromPrefix(prefix);
		System.out.println(eq3.printInfix());
		System.out.println(eq3.printPostfix());
		System.out.println(eq3.printPrefix());

	}

}
