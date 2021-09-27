import java.util.List;
import java.util.Map;

import edu.polytechnique.mjava.ast.TProcDef;
import edu.polytechnique.mjava.ast.VarDecl;
import edu.polytechnique.xvm.asm.opcodes.GSB;
import edu.polytechnique.xvm.asm.opcodes.PUSH;
import edu.polytechnique.xvm.asm.opcodes.RET;
import edu.polytechnique.xvm.asm.opcodes.STOP;

public class ProgramCodeGen {
	public final CodeGen cg = new CodeGen();

	public static String labelOfProcName(String name) {
		return String.format("__%s", name);
	}

	@SuppressWarnings("unused")
	public void codegen(TProcDef<AbstractExpr, AbstractInstruction> proc) {
		final List<VarDecl> args = proc.getArgs(); // Proc. arguments
		final List<VarDecl> locals = proc.getLocals(); // Proc. locals
		final AbstractInstruction is = proc.getBody(); // Proc. body
		
		
		cg.setProc(proc);
		cg.pushLabel(labelOfProcName(proc.getName()));
		
		for (VarDecl arg : args)
			cg.pushLocalVariable(arg.getName(), args.indexOf(arg) - args.size());
		for (VarDecl lv : locals) 
			cg.pushLocalVariable(lv.getName(), 2+locals.indexOf(lv));
		
		for (VarDecl lv : locals) 
			cg.pushInstruction(new PUSH(0));
		
		is.codegen(cg);
		
		if(!proc.getRtype().isPresent()) cg.pushInstruction(new RET());
		
		cg.clearLocals();
	}

	public void codegen(List<TProcDef<AbstractExpr, AbstractInstruction>> procs) {
		for (TProcDef<AbstractExpr, AbstractInstruction> proc : procs)
			this.codegen(proc);
	}

	public ProgramCodeGen() {
		this.cg.pushInstruction(new GSB(labelOfProcName("main")));
		this.cg.pushInstruction(new STOP());
	}
	
	public ProgramCodeGen(Map<String, RecordsInfo> types) {
		this.cg.pushInstruction(new GSB(labelOfProcName("main")));
		this.cg.pushInstruction(new STOP());
		this.cg.types = types;
	}
}
