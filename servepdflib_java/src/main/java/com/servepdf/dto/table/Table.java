package com.servepdf.dto.table;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class Table {
	public List rows = new ArrayList();
	
	public String toString(){
		return this.toXML();
	}
	
	public String toXML(){
		XStream xstream = new XStream();
		xstream.alias("table", Table.class);		
		xstream.alias("tr", Row.class);
		xstream.alias("th", HeaderCell.class);
		xstream.alias("td", Cell.class);
		xstream.addImplicitCollection(Table.class, "rows");
		xstream.addImplicitCollection(Row.class, "cells");
		xstream.registerConverter(new CellXMLConvertor());
		return xstream.toXML(this);
	}
}
