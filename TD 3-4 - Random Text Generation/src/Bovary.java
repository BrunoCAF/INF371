
class Bovary {
	
	static HMap buildTable(String[] files, int n) {
		HMap hm = new HMap();
		
		WordReader wr;
		for(String file : files) {
			wr = new WordReader(file);
			Prefix p = new Prefix(n);
			
			for (String w = wr.read(); w != null; p = p.addShift(w), w = wr.read())	hm.add(p, w);
			
			hm.add(p, Prefix.end);
		}
		
		return hm;
	}
	
	static void generate(HMap t, int n) {
		Prefix p = new Prefix(n);
		WordList wl;
		String w;
		int u;
		do {
			wl = t.find(p);
			u = (int) (wl.length()*Math.random());
			w = wl.toArray()[u];
			
			if(!w.equals(Prefix.end)) {
				if(w.equals(Prefix.par)) System.out.println();
				else System.out.print(w+" ");
				p = p.addShift(w);
			}
		}while(!w.equals(Prefix.end));
		System.out.println();
	}
	
	public static void main(String[] args) {
		int n = 3;
		String[] files = new String[35];
		for(int i = 0; i < 35; i++) files[i] = "bovary/"+String.format("%02d", i+1)+".txt";
		//String[] files = new String[5];
		//for(int i = 0; i < 5; i++) files[i] = "miserables/t"+String.format("%d", i+1)+".txt";
		generate(buildTable(files, n), n);
	}
	
}
