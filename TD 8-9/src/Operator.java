
public enum Operator {
	OPEN(0), PLUS(1), MINUS(1), MULT(2), DIV(2); 
	
	private final int precedence;
	private Operator(int precedence) {this.precedence = precedence;}
	public int getPrecedence() {return this.precedence;}
}
