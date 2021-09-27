import java.util.LinkedList;

class E1 extends Exception {}
class E2 extends E1{}

class Exc {

	static Integer f(Object x, Integer y) {
		System.out.println(x.getClass().getTypeName());
		if(x instanceof Integer) System.out.println("Penis");
		return y; 
	}

	public static void main(String[] args) {
		int a = 3, b = 5;
		int c = (a += b) - 1;
		System.out.println(a);
		System.out.println(c);
		System.out.println(f(a, b));
	}
}