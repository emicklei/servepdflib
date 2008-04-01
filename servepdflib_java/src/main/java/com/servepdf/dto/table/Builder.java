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
	public HeaderCell th(String data) {
		if (null == currentRow) throw new RuntimeException("no row available ; use tr()");
		HeaderCell headerCell = new HeaderCell(data);
		currentRow.cells.add(headerCell);
		return headerCell;
	}
	
	/**
	 * Add a new header cell to the current row.
	 * @param data
	 * @param font
	 * @param fontSize
	 * @return HeaderCell
	 */
	public HeaderCell th(String data, String font, String fontSize) {
        if (null == currentRow) throw new RuntimeException("no row available ; use tr()");
        HeaderCell headerCell = new HeaderCell(data,font,fontSize);
		currentRow.cells.add(headerCell);
		return headerCell;
	}
	
	/**
	 * Add a new data cell to the current row.
	 * @param data
	 * @return Cell
	 */
	public Cell td(String data) {
		if (null == currentRow) throw new RuntimeException("no row available ; use tr()");
		Cell cell = new Cell(data);
		currentRow.cells.add(cell);
		return cell;
	}

	/**
	 * Add a new data cell to the current row.
	 * @param data
	 * @param font
	 * @param fontSize
	 */
	public Cell td(String data, String font, String fontSize) {
        if (null == currentRow) throw new RuntimeException("no row available ; use tr()");
        Cell cell = new Cell(data,font,fontSize);
		currentRow.cells.add(cell);
		return cell;
	}
	
	/**
	 * Create a new row. If a row was currently build then add that row to the
	 * table.
	 * @return Row
	 */
	public Row tr(String height) {
		currentRow = new Row();
        currentRow.height = height;
        table.rows.add(currentRow);
        return currentRow;
	}
}
