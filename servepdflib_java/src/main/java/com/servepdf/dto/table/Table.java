/*
       Copyright 2008 PhilemonWorks.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. 
*/
package com.servepdf.dto.table;

import java.util.ArrayList;
import java.util.List;

import com.servepdf.dto.ValidationException;
import com.thoughtworks.xstream.XStream;

public class Table extends AppearanceHolder {
	public List<Row> rows = new ArrayList<Row>();
	public String columnWidths;
	public String field;
	public String x;
	public String y;
	
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
		xstream.omitField(AppearanceHolder.class, "appearance");
		
		Row.setup(xstream);
		Cell.setup(xstream);
		HeaderCell.setup(xstream);	
	}
	private static XStream newXStream() {
		XStream xstream = new XStream();
		Table.setup(xstream);
		return xstream;
	}
}
