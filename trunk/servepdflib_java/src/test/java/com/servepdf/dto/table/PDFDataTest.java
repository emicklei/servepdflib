package com.servepdf.dto.table;

import com.servepdf.dto.Content;
import com.servepdf.dto.Head;
import com.servepdf.dto.PDFData;
import com.servepdf.dto.TextField;

import junit.framework.TestCase;

public class PDFDataTest extends TestCase {
	
	private PDFData data;
	
	public void setUp(){
		data = new PDFData();
	}
	
	public void testDataWithBodyWithFields(){
		data.addText("test", "value");
		TextField tf = (TextField)(data.body.getContent().getFields().get(0));
		assertNotNull(tf);
		assertEquals("test",tf.name);
		assertEquals("value",tf.value);
	}
	
	public void testDataWithBodyWithFieldsWithSerialization(){
	    data.head.accessKey = "accessKey";
	    data.head.applicationKey = "applicationKey";
	    data.head.documentName = "outputFileName";
	    data.head.cacheControl = Head.CACHE_ASSETS;
	    data.head.unserviceableURL = "unserviceableURL";
		data.addText("test", "value");
		Content content = data.body.getContentList().get(0);
		content.formURL = "http://here.there.com";
		Table table = new Table();
		table.columnWidths = "100,10";
		table.setFontSize("12");
        data.body.getContentList().get(0).getTables().add(table);
		PDFData atad = toXmlAndBack(data);
		// test if everything was read
		assertEquals(data.head.accessKey, atad.head.accessKey);
		assertEquals(data.head.applicationKey, atad.head.applicationKey);
		assertEquals(data.head.documentName, atad.head.documentName);
		assertEquals(data.head.unserviceableURL, atad.head.unserviceableURL);
		assertEquals(data.head.cacheControl, atad.head.cacheControl);
		Content tentnoc = atad.body.getContent();
		assertEquals(content.formURL, tentnoc.formURL);
		TextField tf = (TextField)(content.getFields().get(0));
		assertNotNull(tf);
		assertEquals("test",tf.name);
		assertEquals("value",tf.value);
	}	
	private PDFData toXmlAndBack(PDFData input){
	    System.out.println(input.toXML());
		return PDFData.fromXML(input.toXML());
	}
}
