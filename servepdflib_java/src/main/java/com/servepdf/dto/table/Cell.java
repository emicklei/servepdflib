package com.servepdf.dto.table;

public class Cell {
	public String contents;
	
	public Cell(String contents){
		this.contents = contents;
	}
	
	public String toString(){
		return contents;
	}
}
