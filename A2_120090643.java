import java.util.*;
public class A2_120090643 {
	public static class Tree_Node{
		Tree_Node(int Element,Tree_Node lt,Tree_Node rt){
			element = Element;
			height = 0;
			left = lt;
			right = rt;}
		public int element;
		public int height;
		public Tree_Node left;
		public Tree_Node right;
	}
	public static Tree_Node root;
	Tree_Node rotate(Tree_Node node,int d) {
		if(d==0){//rotate with RightChild
			Tree_Node node1 = node.right;
			node.right = node1.left;
			node1.left = node;
			node.height = Math.max(get_height(node.left),get_height(node.right))+1;
			node1.height = Math.max(get_height(node1.left),get_height(node1.right))+1;
			return node1;}
		else{//rotate with LeftChild
			Tree_Node node2 = node.left;
			node.left = node2.right;
			node2.right = node;
			node.height = Math.max(get_height(node.left),get_height(node.right))+1;
			node2.height = Math.max(get_height(node2.left),get_height(node2.right))+1;
			return node2;
		}
	}

	Tree_Node double_rotate(Tree_Node node,int d){
		if(d==0){//double with left child
			node.left = rotate(node.left,0);
			return rotate(node,1);

		}
		else{//double with right child
			node.right = rotate(node.right,1);
			return rotate(node,0);
		}}
	private int get_height(Tree_Node node) {
		if(node==null)return -1;
		else return node.height;
	}
	private Tree_Node balance(Tree_Node node) {
		if(node==null)return null;
		int gap=get_height(node.left) - get_height(node.right);
		if (gap >1) {
			if (get_height(node.left.left)>=get_height(node.left.right)) {
				// Rotate right
				node = rotate(node,1);
			} else {
				//Double Rotate right
				node = double_rotate(node,0);
			}
		}
		if (gap<- 1) {
			if (get_height(node.right.right)>=get_height(node.right.left)) {
				// Rotate left
				node = rotate(node,0);
			} else {
				// Double Rotate left
				node = double_rotate(node,1);
			}
		}

		return node;
	}

	public void insert(int value){
		root = inter_insert(root,value);
	}
	private Tree_Node inter_insert(Tree_Node node, int value) {
		if(node == null) {
			return new Tree_Node(value,null,null);
		}
		if(value <= node.element) {

			node.left = inter_insert(node.left,value);
		}else {
			node.right = inter_insert(node.right,value);
		}
		node.height = Math.max(get_height(node.left),get_height(node.right))+1;
		return balance(node);
	}

	public void remove(int value) {
		root = inter_remove(root,value);
	}
	private int findMin(Tree_Node node) {
		if(node==null)return 0;
		while(node.left !=null) {
			node = node.left;
		}
		return node.element;
	}
	private Tree_Node inter_remove(Tree_Node node, int value) {
		if(node == null) {
			return null;
		}
		if(value < node.element) {
			node.left = inter_remove(node.left,value);
		}else if(value > node.element) {
			node.right = inter_remove(node.right,value);
		}else {
			if(node.left == null) return node.right;
			else if(node.right == null) return node.left;
			node.element = findMin(node.right);
			node.right = inter_remove(node.right,node.element);
		}
		node.height = Math.max(get_height(node.left),get_height(node.right))+1;
		return balance(node);
	}
	public int find_Successor(Tree_Node node,int value){
		if (node == null) return 1919810;
		if (value >= node.element) return find_Successor(node.right,value);
		else {
			return Integer.min(node.element, find_Successor(node.left, value));
		}
	}

	public int find_Predecessor(Tree_Node node,int value) {
		if (node == null) return 0;
		if (value <= node.element) return find_Predecessor(node.left, value);
		else {
			return Integer.max(node.element, find_Predecessor(node.right, value));
		}
	}


	public int find_rank(Tree_Node root,int x) {
		if(root == null) {
			return 0;
		}
		if(root.element >=x) {
			return find_rank(root.left,x);
		}else {
			return 1 + find_rank(root.left,x) + find_rank(root.right,x);
		}
	}
	public int find_value(Tree_Node node,int value) {
		Stack<Tree_Node> Node_stack = new Stack<>();
		int count = 0;
		while(true) {
			while(node != null) {
				Node_stack.push(node);
				node = node.left;
			}
			node = Node_stack.pop();
			count++;
			if(count==value) {
				return node.element;
			}
			node = node.right;
		}
	}


	public static void main(String[] args){
//		String fileName = "input6.txt";
//		Scanner scan = new Scanner(new FileReader(fileName));
		Scanner scan = new Scanner(System.in);
		int command = scan.nextInt();
		A2_120090643 object = new A2_120090643();
		for(int i=0;i<command;i++){
			int operation = scan.nextInt();
			int number = scan.nextInt();
			if(operation == 1)object.insert(number);
			else if(operation == 2)object.remove(number);
			else if(operation == 3)
				System.out.println((object.find_rank(root,number)+1));
			else if(operation == 4)
				System.out.println(object.find_value(root,number));
			else if(operation == 5)
				System.out.println(object.find_Predecessor(root,number));
			else if(operation == 6)
				System.out.println(object.find_Successor(root,number));

		}

	}}
