import java.util.*;

import edu.polytechnique.mjava.ast.TProcDef;
import edu.polytechnique.xvm.asm.*;
import edu.polytechnique.xvm.asm.interfaces.*;

public final class CodeGen {
	private Vector<AsmInstruction> instructions;
	private Map<String, Integer>   labels;
	private Map<String, Integer>   offsets;
	private TProcDef<AbstractExpr, AbstractInstruction> proc;
	Map<String, RecordsInfo> types;

	public CodeGen() {
		this.instructions = new Vector<AsmInstruction>();
		this.labels = new HashMap<String, Integer>();
		this.offsets = new HashMap<String, Integer>();
		this.proc = null;
		this.types = new HashMap<String, RecordsInfo>();
	}

	public CodeGen(Map<String, RecordsInfo> types) {
		this();
		this.types = types;
	}

	@SuppressWarnings("unused")
	private static int labelc = 0;

	public static String generateLabel() {
		// Generate a fresh label using `labelc'.
		// For example, lXXX where XXX is the value of labelc.
		// Two generated labels should never be equal.
		// A label must start with a lowercase letter.
		return String.format("l%d", labelc++);
	}

	public void pushLabel(String label) {
		labels.put(label, instructions.size());
	}

	public void pushInstruction(AsmInstruction asm) {
		instructions.add(asm);
	}

	public void pushLocalVariable(String name, int offset) {
		offsets.put(name, offset);
	}

	public void clearLocals() {
		this.offsets.clear();
	}

	public int getOffset(String name) {
		return offsets.get(name);
	}

	@Override
	public String toString() {
		return Printer.programToString(this.instructions, this.labels);
	}

	public TProcDef<AbstractExpr, AbstractInstruction> getProc() {
		return proc;
	}

	public void setProc(TProcDef<AbstractExpr, AbstractInstruction> proc) {
		this.proc = proc;
	}
}
