package typo;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Box {

	double width, ascent, descent, stretchingCapacity;
	
	public abstract double getWidth();

	public abstract double getAscent();

	public abstract double getDescent();

	public abstract double getStretchingCapacity();

	public String toString() {
		return String.format("[w=%g, a=%g, d=%g, sC=%g]", this.getWidth(), this.getAscent(),
	        this.getDescent(), this.getStretchingCapacity());
	}
	
	final static boolean DEBUG = false;
	public final boolean draw(Graphics graph, double x, double y, double w) {
		if (DEBUG) {
			graph.setColor(Color.red);
			graph.drawRect((int) x, (int) y, (int) w, (int) (getAscent() + getDescent()));
			graph.setColor(Color.black);
		}
		return doDraw(graph, x, y, w);
	}

	public abstract boolean doDraw(Graphics graph, double x, double y, double w);

}
