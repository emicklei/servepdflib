package com.servepdf.dto.table;

import com.servepdf.dto.PDFData;
import com.servepdf.dto.TextField;

import junit.framework.TestCase;

public class PDFDataTest extends TestCase {
	
	private PDFData data;
	
	public void setUp(){
		data = new PDFData();
	}
	
	public void testDataWithBodyWithFields(){
		data.body.addText("test", "value");
		TextField tf = (TextField)(data.body.getFields().get(0));
		assertNotNull(tf);
		assertEquals("test",tf.name);
		assertEquals("value",tf.value);
	}
	
	public void testDataWithBodyWithFieldsWithSerialization(){
		data.body.addText("test", "value");
		
		data = toXmlAndBack(data);
		
		TextField tf = (TextField)(data.body.getFields().get(0));
		assertNotNull(tf);
		assertEquals("test",tf.name);
		assertEquals("value",tf.value);
	}	
	private PDFData toXmlAndBack(PDFData input){
		return PDFData.fromXML(input.toXML());
	}
}
