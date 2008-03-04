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

    public void marshal(Object value, HierarchicalStreamWriter writer,
            MarshallingContext context) {
    	Cell c = (Cell) value;
    	writer.addAttribute("font", c.font);
    	writer.addAttribute("fontSize", c.fontSize);
    	writer.setValue(c.contents);
    }

public Object unmarshal(HierarchicalStreamReader reader,
            UnmarshallingContext context) {           
    	reader.moveDown();
    	Cell c = new Cell(reader.getValue(),reader.getAttribute("font"),reader.getAttribute("fontSize"));
    	reader.moveUp();
    	return c;
	}
}
