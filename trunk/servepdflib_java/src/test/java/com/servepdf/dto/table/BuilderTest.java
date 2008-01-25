package com.servepdf.dto.table;

import junit.framework.TestCase;

public class BuilderTest extends TestCase {
	
	public void testOneCell(){
		Builder b = new Builder();
		b.tr();
		b.th("Title");
		b.tr();
		b.td("ServePDF");
		b.tr();
		System.out.println(b.table);
	}
}
