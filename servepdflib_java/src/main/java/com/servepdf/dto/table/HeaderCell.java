package com.servepdf.dto.table;

import com.thoughtworks.xstream.XStream;

public class HeaderCell extends Cell {
	
	public HeaderCell(String contents){
		super(contents);		
	}
	
	public HeaderCell(String contents, String font, String fontSize){
        super(contents,font,fontSize);                
	}
	public static void setup(XStream xstream) {
		xstream.registerConverter(new CellXMLConvertor());
		xstream.alias("th", HeaderCell.class);
	}	
}
