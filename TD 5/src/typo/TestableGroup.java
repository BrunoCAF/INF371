package typo;

import java.awt.Graphics;

public class TestableGroup extends Group {
	
	@Override
	public boolean doDraw(Graphics graph, double x, double y, double w) {
		return true;
	}
}
