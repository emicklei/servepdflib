package com.servepdf.dto.table;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

public class CellXMLConvertor extends AbstractSingleValueConverter {

	public boolean canConvert(Class clazz) {
        return Cell.class.isAssignableFrom(clazz);
	}

	public Object fromString(String str) {
        return new Cell(str);
	}
}
