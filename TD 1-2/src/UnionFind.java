import java.lang.reflect.Array;

public class UnionFind {
	static int equiv[], height[];
	
	static void init(int len) {
		equiv = new int[len+2];
		height = new int[len+2];
		for(int k = 0; k < len+2; k++) {equiv[k] = k; height[k] = 0;}
	}

	static int find(int x) {
		//return naiveFind(x);
		//return fastFind(x);
		return logFind(x);
	}
	
	static int naiveFind(int x) {
		return equiv[x];
	}
	
	static int fastFind(int x) {
		int reprCanon = x;
		while(equiv[reprCanon] != reprCanon) reprCanon = equiv[reprCanon];
		return reprCanon;
	}

	static int logFind(int x) {
		int reprCanon = x;
		while(reprCanon != equiv[reprCanon]) {
			equiv[reprCanon] = equiv[equiv[reprCanon]];
			reprCanon = equiv[reprCanon];
		}
		return reprCanon;
	}
	
	static int union(int x, int y) {
		//return naiveUnion(x, y);
		//return fastUnion(x, y);
		return logUnion(x, y);
	}
	
	static int naiveUnion(int x, int y) {
		int repr = equiv[x];
		for(int i = 0; i < Array.getLength(equiv); i++)  
			if(equiv[i] == repr) 
				equiv[i] = equiv[y];
		
		return equiv[y];
	}
	
	static int fastUnion(int x, int y) {
		return equiv[fastFind(x)] = fastFind(y);
	}
	
	static int logUnion(int x, int y) {
		int racineX = logFind(x), racineY = logFind(y), repr;
		if(height[racineX] > height[racineY]) {
			repr = equiv[racineY] = racineX;
		} else {
			repr = equiv[racineX] = racineY;
			height[racineY] = Math.max(height[racineY], height[racineX] + 1);
		}
		return repr;
	}
	
}
