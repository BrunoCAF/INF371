
public class Tokenizer {
	public boolean isStart;
	public boolean isMinUnary;
	public boolean isRetrieval;
	public boolean isIntNum;
	public boolean isNonIntNum;
	public boolean isNeg;
	public double num;
	public int decimalDigits;
	public Calculator calc;

	public Tokenizer() {
		this.reset();
		this.calc = new Calculator();
	}

	private void reset() {
		this.isStart = true;
		this.isMinUnary = true;
		this.isRetrieval = false;
		this.isIntNum = false;
		this.isNonIntNum = false;
		this.isNeg = false;
		this.num = 0;
		this.decimalDigits = 0;
	}
	
	public void readChar(char c) {
		boolean isNum = isIntNum || isNonIntNum;
		if(Character.isDigit(c)) {
			isStart = false; isMinUnary = false;
			num = (isNum ? (isIntNum ? 10 : 1)*num : 0) + 
					Character.getNumericValue(c) * Math.pow(10, - (isNonIntNum ? ++decimalDigits : 0));
			isIntNum = !isNonIntNum; 
		}else {
			if(c == '.') {isNonIntNum = true; decimalDigits = 0;}
			else if(isNum) {
				if(isRetrieval) calc.commandReadMemory((int) (isNeg ? -num : num));
				else calc.commandDouble(isNeg ? -num : num); 
				isNonIntNum = false; isNeg = false; isRetrieval = false;
			}
			isIntNum = false; isStart = false;
			switch(c) {
				case '=': isStart = true; calc.commandEqual(); break;
				case '+': calc.commandOperator(Operator.PLUS); break;
				case '-': 
					if(isMinUnary) {isNeg = true; break;}
					else {calc.commandOperator(Operator.MINUS); break;}
				case '*': calc.commandOperator(Operator.MULT); break;
				case '/': calc.commandOperator(Operator.DIV); break;
				case '(': calc.commandLPar(); break;
				case ')': calc.commandRPar(); break;
				case '$': isRetrieval = true; break;
			}
			switch(c) {
				case '=': case '(': case '+': case '-': case '*': case '/': isMinUnary = true; break;
				default: isMinUnary = false; break;
				case 'C': this.reset(); this.calc.commandInit();
			}
		}
	}
	
	public void readString(String s) {for (Character c : s.toCharArray()) this.readChar(c);}
	
}
