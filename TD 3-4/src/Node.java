
class Node {
	String head;
	Node next;
	
	Node(String head, Node next){
		this.head = head;
		this.next = next;
	}
	
	static int lengthRec(Node l) {
		return l == null ? 0 : 1 + lengthRec(l.next);
	}
	
	static int length(Node l) {
		int len = 0;
		for(Node n = l; n != null; n = n.next) len++;
		return len;
	}
	
	static String printNodes(Node l) {
		String display = "[";
		for(Node n = l; n != null; n = n.next) display += n.head+ (n.next == null ? "" : ", ");
		return display+"]";
	}
	
	static void addLast(String s, Node l) {
		Node n;
		for(n = l; n.next != null; n = n.next);
		n.next = new Node(s, null);
	}
	
	static Node copy(Node the) {
		if(the == null) return the;
		Node copie = new Node(the.head, null), n = copie;
		while(the.next != null) {
			the = the.next;
			n = n.next = new Node(the.head, null); 
		}
		return copie;
	}
	
	static Node insert(String s, Node l) {
		if(l == null || s.compareTo(l.head) <= 0) return new Node(s, l);
		for(Node n = l; n.next != null; n = n.next) {
			if(s.compareTo(n.next.head) <= 0) {
				n.next = new Node(s, n.next);
				return l;
			}
		}
		addLast(s, l);
		return l;
	}
	
	static Node insertionSort(Node l) {
		Node triee = null;
		for(Node n = l; n != null; n = n.next) triee = insert(n.head, triee);
		return triee;
	}
	
	static Node merge(Node l1, Node l2) {
		Node fusionnee = null;
		boolean choix = (l1.head.compareTo(l2.head) <= 0);
		if(choix) {
			fusionnee = new Node(l1.head, null);
			l1 = l1.next;
		} else {
			fusionnee = new Node(l2.head, null);
			l2 = l2.next;
		}
		Node n;
		for(n = fusionnee; l1 != null || l2 != null; n = n.next) {
			if(l1 == null) choix = false;
			else if(l2 == null) choix = true;
			else choix = (l1.head.compareTo(l2.head) <= 0);
			n.next = new Node(choix ? l1.head : l2.head, null);
			if(choix) l1 = l1.next; 
			else l2 = l2.next;
		}
		return fusionnee;
	}
	
	static Node mergeSort(Node l) {
		int taille = length(l);
		if(taille < 2) return l;
		
		Node left = l, mid = l, right;
		for(int i = 0; i < taille/2 - 1; i++) mid = mid.next;
		right = mid.next; mid.next = null;
		
		return merge(mergeSort(left), mergeSort(right));
	}
	
	public static void main(String[] args) {
		Node foobar = new Node("foo", new Node("bar", new Node("baz", null)));
		System.out.println(Node.printNodes(foobar));
		Node.addLast("qux", foobar);
		System.out.println(Node.printNodes(foobar));
		foobar = Node.mergeSort(foobar);
		System.out.println(Node.printNodes(foobar));
	}
}
