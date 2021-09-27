import edu.polytechnique.xvm.asm.opcodes.*;

@SuppressWarnings("unused")
public final class IIf extends AbstractInstruction {
	public final AbstractExpr        condition; // condition (<> 0 => true)
	public final AbstractInstruction iftrue   ; // if "true  (<> 0)" branch
	public final AbstractInstruction iffalse  ; // if "false (== 0)" branch

	public IIf(AbstractExpr condition,
			AbstractInstruction iftrue,
			AbstractInstruction iffalse)
	{
		this.condition = condition;
		this.iftrue = iftrue;
		this.iffalse = iffalse;
	}

	@Override
	public void codegen(CodeGen cg) {
		String lbl1 = CodeGen.generateLabel();
		String lbl2 = CodeGen.generateLabel();

		this.condition.codegen(cg);
		cg.pushInstruction(new GTZ(lbl2));
		this.iftrue.codegen(cg);
		cg.pushInstruction(new GTO(lbl1));
		cg.pushLabel(lbl2);
		this.iffalse.codegen(cg);
		cg.pushLabel(lbl1);
	}
}
