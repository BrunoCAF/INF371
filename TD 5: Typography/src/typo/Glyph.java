package typo;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;


public class Glyph extends Box {

	final private static FontRenderContext frc = new FontRenderContext(null, false, false);
	final private Font font;
	final private char[] chars;
	final private Rectangle2D bounds;

	public Glyph(Font font, char c) {
		this.font = font;
		this.chars = new char[]{c};

		TextLayout layout = new TextLayout("" + chars[0], this.font, frc);
		this.bounds = layout.getBounds();		

		ascent  = - bounds.getY();
		descent = bounds.getHeight() + bounds.getY();
		width   = bounds.getWidth();
	}

	@Override
	public String toString() {
		return String.format("Glyph(%s)%s", chars[0], super.toString());
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
		return 0;
	}

	@Override
	public boolean doDraw(Graphics graph, double x, double y, double w) {
		graph.setFont(font);
		graph.drawChars(chars, 0, chars.length,	(int) (x - bounds.getX()), (int) (y - bounds.getY()));

		return true;
	}

}
