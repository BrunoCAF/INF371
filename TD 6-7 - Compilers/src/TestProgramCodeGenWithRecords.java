import java.io.IOException;
import java.util.Map;

import edu.polytechnique.mjava.ast.TProgram;
import edu.polytechnique.mjava.ast.factory.Factory;
import edu.polytechnique.mjava.parser.MJavaParseError;
import edu.polytechnique.mjava.toplevel.MJavaTop;
import edu.polytechnique.mjava.typing.exn.MJavaTypingError;

public final class TestProgramCodeGenWithRecords {
	public final static String PATH = "factrec.wil";

	private static final Factory<AbstractExpr, AbstractInstruction> factory
	= Factory.ofPackage(
			AbstractExpr.class, null, AbstractInstruction.class, null);

	public static void main(String[] args) throws IOException {
		try {
			TProgram<AbstractExpr, AbstractInstruction> prg =
					MJavaTop.parseAndTypeXProgramFromFile(PATH, factory);
			Map<String, RecordsInfo> types = RecordsInfo.visit(prg.records);
			ProgramCodeGen cg = new ProgramCodeGen(types); // Store `types` somewhere
			
			cg.codegen(prg.procs);
			System.out.print(cg.cg);
		} catch (MJavaParseError | MJavaTypingError e) {
			System.err.println(e.getMessage());
		}
	}
}
