package it.polito.ai.lab3.web.controllers.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Colors {
	private static Colors colors;
	private List<String> colorsArray = new ArrayList<String>();
	private int index;
	private int size;
	
	private Colors() {
		// Fill the array with colors
		colorsArray.add(colorToHexString(Color.ORANGE));
		colorsArray.add(colorToHexString(Color.CYAN));
		colorsArray.add(colorToHexString(Color.GREEN));
		colorsArray.add(colorToHexString(Color.CYAN));
		colorsArray.add(colorToHexString(Color.GRAY));
		colorsArray.add(colorToHexString(Color.MAGENTA));
				
		index = 0;
		size = colorsArray.size();
	}
	
	public static Colors getInstance() {
		if (colors == null)
			colors = new Colors();
		
		return colors;
	}
	
	public String getNextColor() {
		int i = (index++ % size);
		
		return colorsArray.get(i);
	}
	
	private String colorToHexString(Color c) {
		return String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
	}
}
