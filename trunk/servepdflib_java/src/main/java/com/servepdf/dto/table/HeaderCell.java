package com.servepdf.dto.table;

public class HeaderCell extends Cell {
	
	public HeaderCell(String contents){
		super(contents);		
	}
	
	public HeaderCell(String contents, String font, String fontSize){
        super(contents,font,fontSize);                
	}
}
