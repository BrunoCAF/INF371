import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

import typo.Box;

public class Page extends JComponent {
	private static final long serialVersionUID = 1L;

	Box b;
	int textWidth;
	int textHeight;
	int windowWidth;
	int windowHeight;
	final static int leftMargin = 50;
	final static int rightMargin = 50;
	final static int topMargin = 50;

	public void paint(Graphics g) {
		g.drawLine(0, topMargin, windowWidth, topMargin);
		g.drawLine(leftMargin, 0, leftMargin, windowHeight);
		g.drawLine(leftMargin + textWidth, 0, leftMargin + textWidth, windowHeight);
		boolean ok = b.draw(g, leftMargin, topMargin, textWidth);
		if (! ok) System.out.println("overfull hbox!");
	}

	public Page(Box b, int w, int h) {
		this.b = b;
		this.textWidth = w;
		this.textHeight = h;
		windowWidth = textWidth + leftMargin + rightMargin;
		windowHeight = topMargin + textHeight;
		// construire une fenêtre
		final JFrame f = new JFrame("Typographie (échap pour fermer)");
		f.setSize(windowWidth, windowHeight); // dimensions
		f.add(this); // qui contient notre JComponent
		f.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) f.dispose();
			}
		});
		// pour terminer le programme
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// il faut le dire !
		f.setVisible(true);
	}

	public Page(Box b, int w) {
		this(b, w, 300);
	}

}
