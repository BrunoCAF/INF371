import edu.polytechnique.xvm.asm.opcodes.*;

@SuppressWarnings("unused")
public final class ENew extends AbstractExpr {
	private String name; // Record type (name)

	public ENew(String name) {
		this.name = name;
	}

	@Override
	public void codegen(CodeGen cg) {
		cg.pushInstruction(new PUSH(cg.types.get(name).size));
		cg.pushInstruction(new ALLOC());;
	}
}
