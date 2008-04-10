/*
       Copyright 2007 Ernest Micklei, PhilemonWorks.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. 
 */
package com.servepdf.dto;

import java.io.InputStream;

import com.servepdf.dto.table.Table;
import com.thoughtworks.xstream.XStream;

/**
 * PDFData is a DTO that contains information for PDF Form processing
 * 
 * @author ernest
 */
public class PDFData {
	public static final XStream XMLIO = PDFData.newXStream();	
	public Head head = new Head();
	public Body body = new Body();
	

	/**
	 * Create a new PDFData from the XML representation of PDFData. This method
	 * is also required for the Pocogese framework.
	 * 
	 * @return PDFData
	 */
	public static PDFData fromXML(String xml) {
		return (PDFData) (XMLIO.fromXML(xml));
	}

	public static PDFData fromXMLStream(InputStream is) {
		return (PDFData) (XMLIO.fromXML(is));
	}

	private static XStream newXStream() {
		XStream xstream = new XStream();
		xstream.alias("pdfdata", PDFData.class);
		Content.setup(xstream);
		Body.setup(xstream);
		Head.setup(xstream);
		TextField.setup(xstream);
		Table.setup(xstream);
		Mail.setup(xstream);
		return xstream;
	}

	/**
	 * Return the XML representation of PDFData. This method is also required
	 * for the Pocogese framework.
	 * 
	 * @return xml encoded PDFData
	 */
	public String toXML() {
		return XMLIO.toXML(this);
	}

	public void validate() throws ValidationException {
		ValidationException.throwIfNull(head, "pdfdata.head");
		head.validate();
		ValidationException.throwIfNull(body, "pdfdata.body");
		body.validate();
	}

	/**
	 * For debugging
	 */
	public String toString() {
		return toXML();
	}
}
