import java.io.IOException;
import java.util.List;

import edu.polytechnique.mjava.ast.TProcDef;
import edu.polytechnique.mjava.parser.MJavaParseError;
import edu.polytechnique.mjava.typing.exn.MJavaTypingError;

public final class TestProgramCodeGen {
	public final static String PATH = "short-circuit.wil";

	public static void main(String[] args) throws IOException {
		try {
			List<TProcDef<AbstractExpr, AbstractInstruction>> prg =
					ParseAndType.parseAndTypeProgramFromFile(PATH);
			ProgramCodeGen cg = new ProgramCodeGen();

			cg.codegen(prg);
			System.out.print(cg.cg);
		} catch (MJavaParseError | MJavaTypingError e) {
			System.err.println(e.getMessage());
		}
	}
}
