package com.servepdf.dto.table;

public class Cell {
	public String contents;
	public String font;
    public String fontSize;
	
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
