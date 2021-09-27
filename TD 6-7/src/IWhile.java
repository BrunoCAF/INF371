import edu.polytechnique.xvm.asm.opcodes.*;

public final class IWhile extends AbstractInstruction {
	public final AbstractExpr        condition; // loop condition
	public final AbstractInstruction body     ; // loop body

	public IWhile(AbstractExpr condition, AbstractInstruction body) {
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void codegen(CodeGen cg) {
		String lbl1 = CodeGen.generateLabel();
		String lbl2 = CodeGen.generateLabel();

		// Push start of loop label
		cg.pushLabel(lbl1);
		// Assemble condition
		this.condition.codegen(cg);
		// If condition is 0, jump to lbl2
		cg.pushInstruction(new GTZ(lbl2));
		// Generate code for the body
		this.body.codegen(cg);
		// To be continued...
		cg.pushInstruction(new GTO(lbl1));
		cg.pushLabel(lbl2);
	}
}
