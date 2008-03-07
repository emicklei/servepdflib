package com.servepdf.dto.table;

public class Cell {
	public String contents;
	public String font;
    public String fontSize;
	// captures multiple attributes, if set then the non-null values 
	// override those from the containing Row or containing Table.
    public CellAppearance appearance = null;    
	
	public Cell(String contents){
		this.contents = contents;
		this.font = "Verdana";
		this.fontSize = "10";
	}
	
	public Cell(String contents, String font, String fontSize){
        this.contents = contents;
        this.font = font;
        this.fontSize =  fontSize;
	}
	
	public String toString(){
		return contents;
	}
}
