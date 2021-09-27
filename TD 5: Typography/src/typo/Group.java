package typo;

import java.util.LinkedList;

public abstract class Group extends Box {

	protected final LinkedList<Box> list = new LinkedList<Box>();
	
	public Group add(Box boite) {
		list.add(boite);
		return this;
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
	public String toString() {
		String display = super.toString();
		display += "{\n";
		for (Box b : list) 
			display += String.format("   %s,\n", b.toString().replaceAll("\n", "\n   "));
		display += "}";
		return display;
	}

}
