import edu.polytechnique.mjava.ast.Instruction;

public abstract class AbstractInstruction implements Instruction {
	public abstract void codegen(CodeGen cg);

	public boolean isEmpty() {
		return false;
	}
}
