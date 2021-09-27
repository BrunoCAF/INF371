
class HMap {
	static final int taille = 20;
	EntryList[] t;
	int nbEntries;
	
	HMap(int n){
		t = new EntryList[n];
		nbEntries = 0;
	}
	
	HMap(){
		t = new EntryList[taille];
		nbEntries = 0;
	}
	
	WordList find(Prefix key) {
		WordList wl = null;
		int h = key.hashCode(t.length);
		for(EntryList el = t[h]; el != null && wl == null; el = el.next) 
			if(Prefix.eq(key, el.head.key)) 
				wl = el.head.value;
		
		return wl;
	}
	
	void addSimple(Prefix key, String w) {
		int h = key.hashCode(t.length);
		if(t[h] != null) {
			for(EntryList el = t[h]; el != null; el = el.next) {
				if(Prefix.eq(key, el.head.key)) {
					el.head.value.addLast(w);
					return;
				}
			}
		}
		t[h] = new EntryList(new Entry(key, new WordList(new Node(w, null))), t[h]);
		nbEntries++;
	}

	void rehash(int n) {
		EntryList[] tt = new EntryList[n];
		int h;
		for(int k = 0; k < t.length; k++) {
			for(EntryList el = t[k]; el != null; el = el.next) {
				h = el.head.key.hashCode(n);
				tt[h] = new EntryList(el.head, tt[h]);
			}
		}
		t = tt;
	}
	
	void add(Prefix key, String w) {
		if(nbEntries + 1 > 0.75*t.length) rehash(2*t.length);
		addSimple(key, w);
	}
	
}
