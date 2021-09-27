package typo;

import java.awt.Graphics;

public class Hbox extends Group {

	@Override
	public Group add(Box boite) {
		Group g = super.add(boite);
		ascent = Math.max(ascent, boite.getAscent());
		descent = Math.max(descent, boite.getDescent());
		width += boite.getWidth();
		stretchingCapacity += boite.getStretchingCapacity();
		return g;
	}

	@Override
	public String toString() {
		return String.format("Hbox%s", super.toString());
	}

	@Override
	public boolean doDraw(Graphics graph, double x, double y, double w) {
		double mw = getWidth(), dx;
		for (Box b : list) {
			dx = (mw > w) ? 0 :	
				(w-mw) * (stretchingCapacity > 0 ? b.getStretchingCapacity()/stretchingCapacity : 0);
			b.doDraw(graph, x, y + ascent - b.getAscent(), b.getWidth() + dx);
			x += b.getWidth() + dx;
		}
		return w >= mw;
	}

}
