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
	    data.head.accessKey = "accessKey";
	    data.head.applicationKey = "applicationKey";
	    data.head.documentName = "outputFileName";
	    data.head.unserviceableURL = "unserviceableURL";
		data.body.addText("test", "value");
		data.body.contentList.get(0).formURL = "http://here.there.com";
		Table table = new Table();
		table.columnWidths = "100,10";
		table.setFontSize("12");
        data.body.contentList.get(0).getTables().add(table);
		data = toXmlAndBack(data);

		TextField tf = (TextField)(data.body.getFields().get(0));
		assertNotNull(tf);
		assertEquals("test",tf.name);
		assertEquals("value",tf.value);
	}	
	private PDFData toXmlAndBack(PDFData input){
	    System.out.println(input.toXML());
		return PDFData.fromXML(input.toXML());
	}
}
