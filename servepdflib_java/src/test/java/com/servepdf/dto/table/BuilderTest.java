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
}
