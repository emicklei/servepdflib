package com.servepdf.dto.table;

import junit.framework.TestCase;

public class BuilderTest extends TestCase {
	
	public void testOneCell(){
		Builder b = new Builder();
		b.tr("10");
		b.th("Title");
		b.tr("10");
		b.td("ServePDF");
		b.tr("10");
		System.out.println(b.table);
	}
	public void testTableWithAppearance(){
		Builder b = new Builder();
		b.table.setFont("Verdanda");
		b.table.setFontSize("14");		
		b.tr("10").setFont("Helvetica");
		b.th("Title").setFontSize("18");
		System.out.println(b.table);
	}	
}
