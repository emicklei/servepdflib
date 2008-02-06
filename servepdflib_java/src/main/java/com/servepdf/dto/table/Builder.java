package com.servepdf.dto.table;

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
	 * Add a new data cell to the current row.
	 * @param data
	 */
	public void td(String data) {
		if (null == currentRow) throw new RuntimeException("no row available ; use tr()");
		currentRow.cells.add(new Cell(data));
	}

	/**
	 * Create a new row. If a row was currently build then add that row to the
	 * table.
	 */
	public void tr() {
		if (null != currentRow) {
			table.rows.add(currentRow);
		}
		currentRow = new Row();
	}
}