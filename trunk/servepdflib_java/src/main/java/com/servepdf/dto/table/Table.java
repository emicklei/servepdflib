package com.servepdf.dto.table;

import java.util.ArrayList;
import java.util.List;

import com.servepdf.dto.ValidationException;
import com.thoughtworks.xstream.XStream;

public class Table {
	public List<Row> rows = new ArrayList<Row>();
	public String columnWidths;
	public String field;
	public String x;
	public String y;
	// captures multiple attributes, if set then the values applies
	// to all cells unless a Row or an Cell overrides it.
	public CellAppearance appearance = new CellAppearance();
	
	/**
	 * Create a new Table from the XML representation of Table.
	 * 
	 * @return Table
	 */
	public static Table fromXML(String xml) {
		return (Table) (Table.newXStream().fromXML(xml));
	}

	public String toString() {
		return this.toXML();
	}

	public String toXML() {
		return Table.newXStream().toXML(this);
	}

	public void validate() throws ValidationException {
	}

	public static void setup(XStream xstream) {
		xstream.alias("table", Table.class);
		xstream.useAttributeFor("columnWidths", String.class);
		xstream.useAttributeFor("field", String.class);
		xstream.useAttributeFor("x", String.class);
		xstream.useAttributeFor("y", String.class);
		xstream.registerConverter(new CellXMLConvertor());
		xstream.addImplicitCollection(Table.class, "rows");
		xstream.addImplicitCollection(Row.class, "cells");
		xstream.omitField(Table.class, "appearance");
		
		Row.setup(xstream);
		Cell.setup(xstream);
		HeaderCell.setup(xstream);	
	}
	private static XStream newXStream() {
		XStream xstream = new XStream();
		Table.setup(xstream);
		return xstream;
	}
	
	public CellAppearance getAppearance(){
		return appearance;
	}	
}
