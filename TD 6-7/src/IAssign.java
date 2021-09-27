import edu.polytechnique.mjava.ast.LValue;
import edu.polytechnique.xvm.asm.opcodes.*;
import java.util.Optional;

@SuppressWarnings("unused")
public final class IAssign extends AbstractInstruction {
	public final Optional<LValue<AbstractExpr>> lvalue; // (optional) left-value
	public AbstractExpr rvalue; // right-value (expression)

	public IAssign(Optional<String> lvalue, AbstractExpr rvalue) {
		this.lvalue = lvalue.map((x) -> new LValue<AbstractExpr>(x));
		this.rvalue = rvalue;
	}

	public IAssign(LValue<AbstractExpr> lvalue, AbstractExpr rvalue) {
		this.lvalue = Optional.of(lvalue);
		this.rvalue = rvalue;
	}

	@Override
	public void codegen(CodeGen cg) {
		this.rvalue.codegen(cg);

		lvalue.ifPresent(
				lv -> {
					if(lv.target.isPresent()) 
						{lv.target.get().getExpr().codegen(cg);
						String rtypename = lv.target.get().getType().get().asNamed().getName();
						int offset = cg.types.get(rtypename).offsets.get(lv.name);
						cg.pushInstruction(new PUSH(offset));
						cg.pushInstruction(new CWRITE());}
					else
						cg.pushInstruction(new WFR(cg.getOffset(lv.name)));
				});
		if(!lvalue.isPresent()) cg.pushInstruction(new POP());
	}
}
