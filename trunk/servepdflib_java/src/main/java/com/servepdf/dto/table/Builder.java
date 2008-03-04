package com.servepdf.dto.table;
/**
 * Builder can help in constructing a Table instance but providing a fluent interface.
 * 
 * @author ernestmicklei
 */
public class Builder {
	public Table table = new Table();
	public Row currentRow;

	/**
	 * Add a new header cell to the current row.
	 * @param data
	 */
	public void th(String data) {
		if (null == currentRow) throw new RuntimeException("no row available ; use tr()");
		currentRow.cells.add(new HeaderCell(data));
	}
	
	/**
	 * Add a new header cell to the current row.
	 * @param data
	 * @param font
	 * @param fontSize
	 */
	public void th(String data, String font, String fontSize) {
        if (null == currentRow) throw new RuntimeException("no row available ; use tr()");
        currentRow.cells.add(new HeaderCell(data,font,fontSize));
	}
	
	/**
	 * Add a new data cell to the current row.
	 * @param data
	 */
	public void td(String data) {
		if (null == currentRow) throw new RuntimeException("no row available ; use tr()");
		currentRow.cells.add(new Cell(data));
	}

	/**
	 * Add a new data cell to the current row.
	 * @param data
	 * @param font
	 * @param fontSize
	 */
	public void td(String data, String font, String fontSize) {
        if (null == currentRow) throw new RuntimeException("no row available ; use tr()");
        currentRow.cells.add(new Cell(data,font,fontSize));
	}
	
	/**
	 * Create a new row. If a row was currently build then add that row to the
	 * table.
	 */
	public void tr(String height) {
		currentRow = new Row();
        currentRow.height = height;
        table.rows.add(currentRow);
	}
}
