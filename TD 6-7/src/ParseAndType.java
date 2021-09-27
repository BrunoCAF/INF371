import java.io.IOException;
import java.util.List;

import edu.polytechnique.mjava.ast.TProcDef;
import edu.polytechnique.mjava.ast.factory.Factory;
import edu.polytechnique.mjava.parser.MJavaParseError;
import edu.polytechnique.mjava.toplevel.MJavaTop;
import edu.polytechnique.mjava.toplevel.MJavaTop.ExprWithCtxt;
import edu.polytechnique.mjava.toplevel.MJavaTop.InstrWithCtxt;
import edu.polytechnique.mjava.typing.exn.MJavaTypingError;

public final class ParseAndType {
	private static final Factory<AbstractExpr, AbstractInstruction> factory
	= Factory.ofPackage(
			AbstractExpr.class, null, AbstractInstruction.class, null);

	public static List<TProcDef<AbstractExpr, AbstractInstruction>>
	parseAndTypeProgramFromFile(String path)
			throws MJavaParseError, MJavaTypingError, IOException
	{
		return MJavaTop.parseAndTypeProgramFromFile(path, factory);
	}

	public static ExprWithCtxt<AbstractExpr> parseAndTypeExpr(String input)
			throws MJavaParseError, MJavaTypingError
	{
		return MJavaTop.parseAndTypeExpr(input, factory.expr);
	}

	public static
	InstrWithCtxt<AbstractExpr, AbstractInstruction>
	parseAndTypeInstr(String input)
			throws MJavaParseError, MJavaTypingError
	{
		return MJavaTop.parseAndTypeInstr(input, factory);
	}

}
