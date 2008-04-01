package com.servepdf.dto.table;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CellXMLConvertor implements Converter {
	public boolean canConvert(Class clazz) {
		return Cell.class.isAssignableFrom(clazz);
	}

	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
		Cell c = (Cell) value;
		if (c.getAppearance().font != null) {
			writer.addAttribute("font", c.getAppearance().font);
		} 
		if (c.getAppearance().fontSize != null) {
			writer.addAttribute("fontSize", c.getAppearance().fontSize);
		}
		writer.setValue(c.contents);
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Cell c = null;
		String font = reader.getAttribute("font");
		String fontSize = reader.getAttribute("fontSize");
		if (reader.getNodeName().equals("th")) {
			c = new HeaderCell(reader.getValue(), font, fontSize);
		} else {
			c = new Cell(reader.getValue(), font, fontSize);
		}
		return c;
	}
}
