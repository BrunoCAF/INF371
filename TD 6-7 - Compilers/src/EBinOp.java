import edu.polytechnique.xvm.asm.opcodes.*;
import edu.polytechnique.mjava.ast.BinOp;

@SuppressWarnings("unused")
public final class EBinOp extends AbstractExpr {
	public final BinOp        op   ;    // operator (enum)
	public final AbstractExpr left ;    // left operand
	public final AbstractExpr right;    // right operand

	public EBinOp(BinOp op, AbstractExpr left, AbstractExpr right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}

	@Override
	public void codegen(CodeGen cg) {
		switch(this.op) {
			case GT :
			case LE :
				this.right.codegen(cg);
				this.left.codegen(cg);
				break;
			default:
				this.left.codegen(cg);
				this.right.codegen(cg);
		}

		switch(this.op) {
			case ADD:
				cg.pushInstruction(new ADD());
				break;
			case SUB :
				cg.pushInstruction(new SUB());
				break;
			case MUL :
				cg.pushInstruction(new MULT());
				break;
			case DIV :
				cg.pushInstruction(new DIV());
				break;
			case EQ :
				cg.pushInstruction(new EQ());
				break;
			case NEQ :
				cg.pushInstruction(new EQ());
				cg.pushInstruction(new NOT());
				break;
			case LT :
				cg.pushInstruction(new LT());
				break;
			case LE :
				cg.pushInstruction(new LT());
				cg.pushInstruction(new NOT());
				break;
			case GT :
				cg.pushInstruction(new LT());
				break;
			case GE :
				cg.pushInstruction(new LT());
				cg.pushInstruction(new NOT());
				break;
			case AND :
				cg.pushInstruction(new AND());
				break;
			case OR :
				cg.pushInstruction(new OR());
				break;
		}
	}
}
