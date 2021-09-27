package typo;

import java.awt.Graphics;

public class Vbox extends Group {

	private double lineSkip;

	public Vbox(double lineSkip) {
		this.lineSkip = lineSkip;
	}
	
	@Override
	public Group add(Box boite) {
		ascent += (list.isEmpty() ? 0 : lineSkip);
		Group g = super.add(boite);
		ascent += descent + boite.getAscent();
		descent = boite.getDescent();
		width = Math.max(width, boite.getWidth());
		stretchingCapacity = Math.max(stretchingCapacity, boite.getStretchingCapacity());
		return g;
	}
	
	@Override
	public String toString() {
		return String.format("Vbox%s", super.toString());
	}
	
	@Override
	public boolean doDraw(Graphics graph, double x, double y, double w) {
		for (Box b : list) {
			b.doDraw(graph, x, y, w);
			y += b.getAscent() + b.getDescent() + lineSkip;
		}
		return true;
	}

}
