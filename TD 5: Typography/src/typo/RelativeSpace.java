package typo;

import java.awt.Font;

public class RelativeSpace extends Space {

	public RelativeSpace(double c, Font f) {
		super(c * f.getSize(), 1);
	}

}
