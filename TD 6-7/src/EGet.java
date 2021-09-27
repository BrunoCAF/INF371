import edu.polytechnique.xvm.asm.opcodes.*;

@SuppressWarnings("unused")
public final class EGet extends AbstractExpr {
	private final AbstractExpr target; // Record expression
	private final String       tgType; // Record type (name)
	private final String       field ; // Field name

	public EGet(AbstractExpr target, String tgType, String field)
	{
		this.target = target;
		this.tgType = tgType;
		this.field = field;
	}

	@Override
	public void codegen(CodeGen cg) {
		this.target.codegen(cg);
		cg.pushInstruction(new PUSH(cg.types.get(tgType).offsets.get(field)));
		cg.pushInstruction(new CREAD());
	}
}
