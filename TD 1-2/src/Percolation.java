
public class Percolation {
	static int size = 100;
	static int length = size*size;
	
	static boolean grid[];
	
	static boolean ordreDefinie;
	static int compteur;
	static int casesNoires[];
	
	
	static void init() {
		grid = new boolean[length];
		for (int k = 0; k < length; k++) grid[k] = false;
		
		UnionFind.init(length);
	}
	
	static void print() {
		String output = "";
		for(int k = 0; k < length; k++) {
			output += grid[k] ? '*' : '-';
			if(k % size == size-1) output += '\n';
		}
		System.out.print(output);
	}
		
	static int randomShadow() {
		if(ordreDefinie) { 
			//System.out.println("compteur: "+compteur);
			grid[casesNoires[compteur]] = true;
			propagateUnion(casesNoires[compteur]);
			return casesNoires[compteur++];
		} else {
			for (int k = 0; k < length; k++) casesNoires[k] = k;
			for (int j = length-1, i, swap; j > 0; j--) {
				i = (int) ((j+1) * Math.random());
				swap = casesNoires[j];
				casesNoires[j] = casesNoires[i];
				casesNoires[i] = swap;
			}
			ordreDefinie = true;
			return randomShadow();
		}
	}
	
	static void propagateUnion(int x) {
		if(!grid[x]) return;
		int i = x / size, j = x - i*size, ligneBas = length+1, ligneHaut = length;
		boolean isValid[] = {i+1 < size, j - 1 >= 0, j + 1 < size, i - 1 >= 0};
		int voisins[] = {x + size, x - 1, x + 1, x - size};
		
		if(i == 0) {//x na ligneHaut			
			if(UnionFind.equiv[ligneHaut] >= length)//se x é a 1a cN na lH: unificar a lH
				for(int c = 0; c < size; c++) UnionFind.union(c, x);
			UnionFind.union(ligneHaut, x);//unir x e a ligneHaut
		}
		if(i == size - 1) {
			if(UnionFind.equiv[ligneBas] >= length)//se x é a 1a cN na lB: unificar a lB
				for(int c = 0; c < size; c++) UnionFind.union(length-1-c, x);
			UnionFind.union(ligneBas, x);//unir x e a ligneBas
		}
		
		for(int k = 0; k < 4; k++) 
			if(isValid[k]) if(grid[voisins[k]])	UnionFind.union(voisins[k], x);
		
	}
	
	static boolean isPercolation(int n) {
		//return isNaivePercolation(n);
		//return isFastPercolation(n);
		return isLogPercolation();
	}
	
	static boolean isNaivePercolation(int n) {
		boolean seenUp[] = new boolean[length], seenDown[] = new boolean[length];
		for(int k = 0; k < length; k++) seenUp[k] = seenDown[k] = false;
		return detectPath(seenUp, n, true) && detectPath(seenDown, n, false);
	}

	static boolean detectPath(boolean[] seen, int n, boolean up) {
		seen[n] = true; //visiter le case tout de suite
		if(!grid[n]) return false; //si la case est blanche il n'y a pas de chemin
		
		//Cas Base: n est déjà dans la bonne ligne
		if(n / size == (up ? 0 : size-1)) return true;
		
		//Hérédité: quelqu'un des voisins unseen de n a trouvé un chemin
		boolean detection = false;
		int i = n / size, j = n - i*size;
		//Bas: n+size
		if(i + 1 < size)
			if(!seen[n+size]) detection |= detectPath(seen, n+size, up);
		//Gauche: n-1
		if(j - 1 >= 0)
			if(!seen[n-1]) detection |= detectPath(seen, n-1, up);
		//Droite: n+1
		if(j + 1 < size)
			if(!seen[n+1]) detection |= detectPath(seen, n+1, up);
		//Haut: n-size
		if(i - 1 >= 0)
			if(!seen[n-size]) detection |= detectPath(seen, n-size, up);
		
		return detection;
	}
	
	static boolean isFastPercolation(int n) {
		int repr = UnionFind.find(n);
		boolean atteintBas = false, atteintHaut = false;
		for(int i = 0; i < size; i++) {
			if(UnionFind.find(i) == repr) atteintHaut = true;
			if(UnionFind.find(length-1-i) == repr) atteintBas = true;
		}
		return atteintBas && atteintHaut;
	}

	static boolean isLogPercolation() {
		return UnionFind.find(length) == UnionFind.find(length+1);
	}
	
	static double percolation() {
		ordreDefinie = false;
		compteur = 0;
		casesNoires = new int[length];
		
		for(int k = 0; k < size; k++) randomShadow();
		
		boolean percolationFound = false;
		for(int j = 0; j < compteur; j++) percolationFound |= isPercolation(casesNoires[j]);
		
		while(!percolationFound) {
			randomShadow();
			percolationFound |= isPercolation(casesNoires[compteur-1]);
		}
		
		double seuil = (compteur * 1.0) / length;
		ordreDefinie = false;
		compteur = 0;
		casesNoires = new int[length];
		
		return seuil;
	}

	static double monteCarlo(int n) {
		double seuil = 0;
		for(int i = 0; i < n; i++) {
			init();
			seuil += percolation();
		}
		return seuil/n;
	}
	
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		double seuil, temps;
		temps = System.currentTimeMillis();
		seuil = monteCarlo(n);
		temps = System.currentTimeMillis() - temps;
		temps /= n;
		System.out.println("Tableau de taille "+size+"x"+size);
		System.out.println("Seuil estimé après "+n+" simulations: "+seuil);
		System.out.println("Temps moyen d'exécution: "+temps+" ms");
	}
}
