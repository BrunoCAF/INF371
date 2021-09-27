import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Calculator {
	public Stack<Double> numbers;
	public Stack<Operator> operators;
	public LinkedList<Double> results;

	Calculator() {
		numbers = new Stack<Double>();
		operators = new Stack<Operator>();
		results = new LinkedList<Double>();
	}

	private static int precedence(Operator op) {return op.getPrecedence();}

	@Override
	public String toString() {return numbers.toString() + "\n" + operators.toString();}

	public void pushDouble(double d) {numbers.push(d);}

	public void pushOperator(Operator o) {operators.push(o);}

	public double getResult() {
		try { return numbers.lastElement();	}
		catch(RuntimeException E) { 
			throw new NoSuchElementException("La pile <numbers> est vide!"); 
		}
	}

	public void executeBinOperator(Operator op) {
		switch(op) {
			case DIV :
				numbers.push(1.0/numbers.pop() * numbers.pop());
				break;
			case MINUS :
				numbers.push(- numbers.pop() + numbers.pop());
				break;
			case MULT :
				numbers.push(numbers.pop() * numbers.pop());
				break;
			case PLUS :
				numbers.push(numbers.pop() + numbers.pop());
				break;
			default :
				break;
		}
	}

	public void commandOperator(Operator op) {
		while(!operators.empty() && precedence(operators.lastElement()) >= precedence(op)) 
			executeBinOperator(operators.pop());
		pushOperator(op);
	}

	public void commandDouble(double d) {this.pushDouble(d);}

	public void commandEqual() {
		while(!operators.empty()) executeBinOperator(operators.pop());
		if(!numbers.isEmpty())results.push(numbers.lastElement());
	}

	public void commandLPar() {pushOperator(Operator.OPEN);}

	public void commandRPar() {
		while(!operators.lastElement().equals(Operator.OPEN)) executeBinOperator(operators.pop()); 
		operators.pop();
	}

	public void commandInit() { numbers.clear(); operators.clear();	}

	public void commandReadMemory(int i) {
		commandDouble((i > 0) ? results.get(i-1) : -results.get(-i-1));;
	}
	
}
