package com.servepdf.dto.table;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class Table {
	public List rows = new ArrayList();
	public String columnWidths;
    public String field;
	
    public String toString(){
        return this.toXML();
    }

    public String toXML(){
    	return Table.newXStream().toXML(this);
    }
	
	 private static XStream newXStream() {
        XStream xstream = new XStream();
        xstream.alias("table", Table.class);
        xstream.useAttributeFor("columnWidths", String.class);
        xstream.useAttributeFor("field", String.class);
        xstream.alias("tr", Row.class);
        xstream.alias("th", HeaderCell.class);
        xstream.alias("td", Cell.class);
        xstream.addImplicitCollection(Table.class, "rows");
        xstream.addImplicitCollection(Row.class, "cells");
        xstream.useAttributeFor("height", String.class);
        xstream.useAttributeFor("font", String.class);
        xstream.useAttributeFor("fontSize", String.class);
        //xstream.aliasField("", Cell.class, "contents");
        xstream.registerConverter(new CellXMLConvertor());            
       
        return xstream;
	}
}
