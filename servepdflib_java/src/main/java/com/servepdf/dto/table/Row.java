package com.servepdf.dto.table;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class Row extends AppearanceHolder {
	public List<Cell> cells = new ArrayList<Cell>();
	public String height;
	
	public static void setup(XStream xstream) {
		xstream.alias("tr", Row.class);
		xstream.useAttributeFor("height", String.class);
	}	
}
