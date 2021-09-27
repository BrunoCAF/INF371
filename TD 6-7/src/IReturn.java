import edu.polytechnique.xvm.asm.opcodes.*;
import java.util.Optional;
import java.util.Vector;

@SuppressWarnings("unused")
public final class IReturn extends AbstractInstruction {
	public final Optional<AbstractExpr> result; // Value to return

	public IReturn() {
		this(Optional.empty());
	}

	public IReturn(Optional<AbstractExpr> result) {
		this.result = result;
	}

	@Override
	public void codegen(CodeGen cg) {
		result.ifPresent(
				value -> {
					if(value instanceof ECall) {
						Vector<AbstractExpr> args = ((ECall) value).args;
						String name = ((ECall) value).name;
						for (AbstractExpr ae : args) ae.codegen(cg);
						for (AbstractExpr ae : args)
							cg.pushInstruction(new WFR(- args.indexOf(ae) - 1));
						cg.pushInstruction(new GTO(ProgramCodeGen.labelOfProcName(name)));
						return;
					}
					value.codegen(cg); cg.pushInstruction(new PXR());
					});
		cg.getProc().getLocals().forEach(local -> cg.pushInstruction(new POP()));
		cg.pushInstruction(new RET());
	}
}
