
class Prefix {
	String[] t;
	
	final static String start = "<START>", end = "<END>", par = "<PAR>";
	
	Prefix(int n){
		t = new String[n];
		for(int i = 0; i < n; i++) t[i] = Prefix.start;
	}
	
	static boolean eq(Prefix p1, Prefix p2) {
		boolean egalite = p1.t.length == p2.t.length;
		if(egalite) 
			for(int i = 0; i < p1.t.length; i++) 
				egalite &= p1.t[i].equals(p2.t[i]);
		return egalite;
	}
	
	Prefix addShift(String w) {
		Prefix p = new Prefix(t.length);
		for(int i = 0; i < t.length - 1; i++) p.t[i] = t[i+1];
		p.t[t.length - 1] = w;
		return p;
	}
	
	public int hashCode() {
		int h = 0;
		for(int i = 0; i < t.length; i++) h = 37*h + t[i].hashCode();
		return h;
	}
	
	int hashCode(int n) {
		int h = hashCode();
		return h%n < 0 ? h%n + n : h%n;
	}
	
	public static void main(String[] args) {
		Prefix p1 = new Prefix(3), p2 = new Prefix(3);
		System.out.println(Prefix.eq(p1, p2));
	}
}
