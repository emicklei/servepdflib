package com.servepdf.dto.table;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class Row {
	public List<Cell> cells = new ArrayList<Cell>();
	public String height;
	// captures multiple attributes, if set then the values applies
	// to all cells unless a Cell overrides it.
	public CellAppearance appearance = new CellAppearance();
	
	public CellAppearance getAppearance(){
		return appearance;
	}
	public static void setup(XStream xstream) {
		xstream.alias("tr", Row.class);
		xstream.useAttributeFor("height", String.class);
		xstream.omitField(Row.class, "appearance");
	}	
}
