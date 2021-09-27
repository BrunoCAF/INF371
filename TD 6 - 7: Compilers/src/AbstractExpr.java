import edu.polytechnique.mjava.ast.Expr;

public abstract class AbstractExpr implements Expr {
	public abstract void codegen(CodeGen cg);
}
