package typo;

import java.awt.Graphics;

public class Space extends Box {
	
	private double width;
	private double stretchingCapacity;
	
	public Space(double width, double stretchingCapacity) {
		this.width = width;
		this.stretchingCapacity = stretchingCapacity;
		this.ascent = this.descent = 0;
	}

	@Override
	public String toString() {
		return String.format("Space%s", super.toString());
	}
	
	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getAscent() {
		return ascent;
	}

	@Override
	public double getDescent() {
		return descent;
	}

	@Override
	public double getStretchingCapacity() {
		return stretchingCapacity;
	}

	@Override
	public boolean doDraw(Graphics graph, double x, double y, double w) {
		return true;
	}

}
