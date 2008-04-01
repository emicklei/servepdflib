package com.servepdf.dto.table;

import com.thoughtworks.xstream.XStream;

public class Cell {
	public String contents;
	// captures multiple attributes, if set then the non-null values 
	// override those from the containing Row or containing Table.
    private CellAppearance appearance = null;    
	
	public Cell(String contents){
		this.contents = contents;
	}
	
	public Cell(String contents, String font, String fontSize){
        this.contents = contents;
        this.getAppearance().font = font;
        this.getAppearance().fontSize = fontSize;
	}
	
	public String toString(){
		return contents;
	}
	
	public CellAppearance getAppearance(){
		if (appearance == null) appearance = new CellAppearance();
		return appearance;
	}
	
	public static void setup(XStream xstream) {
		xstream.registerConverter(new CellXMLConvertor());
		xstream.alias("td", Cell.class);
		xstream.omitField(Cell.class, "appearance");
	}
}
