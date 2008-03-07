package com.servepdf.dto.table;

import java.util.ArrayList;
import java.util.List;

public class Row {
	public List<Cell> cells = new ArrayList<Cell>();
	public String height;
	// captures multiple attributes, if set then the values applies
	// to all cells unless a Cell overrides it.
	public CellAppearance appearance = null;	
}
