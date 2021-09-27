import edu.polytechnique.xvm.asm.opcodes.*;

@SuppressWarnings("unused")
public final class IPrint extends AbstractInstruction {
	public final AbstractExpr expr; // Expression (int) to print

	public IPrint(AbstractExpr expr) {
		this.expr = expr;
	}

	@Override
	public void codegen(CodeGen cg) {
		this.expr.codegen(cg);
		cg.pushInstruction(new PRT());
	}
}
