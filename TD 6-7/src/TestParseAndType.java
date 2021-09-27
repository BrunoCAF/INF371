import java.io.IOException;

import edu.polytechnique.mjava.parser.MJavaParseError;
import edu.polytechnique.mjava.typing.exn.MJavaTypingError;

public final class TestParseAndType {
	public final static String PATH = "fact.wil";

	public static void main(String[] args) throws IOException {
		try {
			ParseAndType.parseAndTypeProgramFromFile(PATH);
		} catch (MJavaParseError | MJavaTypingError e) {
			System.err.println(e.getMessage());
		}
	}
}
