import java.util.Vector;

import edu.polytechnique.xvm.asm.opcodes.GSB;
import edu.polytechnique.xvm.asm.opcodes.POP;
import edu.polytechnique.xvm.asm.opcodes.PRX;
import edu.polytechnique.xvm.asm.opcodes.PUSH;

public final class ECall extends AbstractExpr {
	public final String               name; // procedure name
	public final Vector<AbstractExpr> args; // arguments

	public ECall(String name, Vector<AbstractExpr> args) {
		this.name = name;
		this.args = args;
	}

	@Override
	public void codegen(CodeGen cg) {
		cg.pushInstruction(new PUSH(0));
		
		for (AbstractExpr ae : args) ae.codegen(cg);
		cg.pushInstruction(new GSB(ProgramCodeGen.labelOfProcName(name)));
		args.forEach(arg -> cg.pushInstruction(new POP()));
		
		cg.pushInstruction(new POP());
		
		cg.pushInstruction(new PRX());
		
		
	}
}
