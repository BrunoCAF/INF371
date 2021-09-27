
class WordList {
	Node content;
	
	WordList() {
		content = null;
	}
	
	WordList(Node content) {
		this.content = content;
	}
	
	WordList(String[] t) {
		if(t.length == 0) content = null;
		else {
			content = new Node(t[0], null);
			Node n = content;
			for(int i = 1; i < t.length; i++, n = n.next) 
				n.next = new Node(t[i], null);
		}
	}
	
	static WordList foobar = new WordList(new Node("foo", new Node("bar", new Node("baz", null))));
	
	String[] toArray() {
		String[] tableaux = new String[this.length()];
		int i = 0;
		for(Node n = content; n != null; n = n.next) tableaux[i++] = n.head;
		return tableaux;
	}
	
	int length() {return Node.length(content);}
	
	String print() {return Node.printNodes(content);}
	
	void addFirst(String w) {content = new Node(w, content);}
	
	void addLast(String w) {
		if(content == null) {content = new Node(w, null); return;}
		Node n;
		for(n = content; n.next != null; n = n.next);
		n.next = new Node(w, null);
	}
	
	String removeFirst() {
		if(content == null)	return null;
		String w = content.head;
		content = content.next;
		return w;
	}
	
	String removeLast() {
		if(content == null)	return null;
		String w;
		if(content.next == null) {
			w = content.head;
			content = content.next;
			return w;
		}
		Node n;
		for(n = content; n.next.next != null; n = n.next);
		w = n.next.head;
		n.next = n.next.next;
		
		return w;
	}
	
	void insert(String s) {content = Node.insert(s, content);}
	
	void insertionSort() {content = Node.insertionSort(content);}
	
	void mergeSort() {content = Node.mergeSort(content);}
	
	public static void main(String[] args) {
		WordList wl = new WordList();
		System.out.println(wl.length());
		wl.addLast("foo");
		System.out.println(wl.length());
	}
	
	
	
}
