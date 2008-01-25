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

import com.thoughtworks.xstream.XStream;

/**
 * PDFData is a DTO that contains information for PDF Form processing
 * 
 * @author ernest
 */
public class PDFData {
	public Head head = new Head();
	public Body body = new Body();

	/**
	 * Create a new PDFData from the XML representation of PDFData.
	 * This method is also required for the Pocogese framework.
	 * @return PDFData
	 */
	public static PDFData fromXML(String xml) {
		return (PDFData) (PDFData.newXStream().fromXML(xml));
	}

	public static PDFData fromXMLStream(InputStream is) {
		return (PDFData) (PDFData.newXStream().fromXML(is));
	}
	private static XStream newXStream() {
		XStream xstream = new XStream();
		xstream.alias("pdfdata", PDFData.class);		
		xstream.addImplicitCollection(Body.class, "fields");
		// Head
		xstream.aliasField("document-url", Head.class, "documentURL");
		xstream.aliasField("cache-control", Head.class, "cacheControl");
		xstream.aliasField("access-key", Head.class, "accessKey");
		xstream.aliasField("unserviceable-url", Head.class, "unserviceableURL");
		// FormField
		xstream.alias("field", TextField.class);
		xstream.useAttributeFor("name", String.class);
		xstream.useAttributeFor("value", String.class);
		xstream.useAttributeFor("version", String.class);
		// Mail
		xstream.alias("mail", Mail.class);
		xstream.useAttributeFor("toAddress", String.class);
		xstream.useAttributeFor("fromAddress", String.class);
		xstream.useAttributeFor("subject", String.class);
		xstream.useAttributeFor("contentType", String.class);
		return xstream;
	}
	/**
	 * Return the XML representation of PDFData.
	 * This method is also required for the Pocogese framework.
	 * @return xml encoded PDFData
	 */
	public String toXML() {
		return PDFData.newXStream().toXML(this);
	}
	
	public void validate() throws ValidationException {
		ValidationException.throwIfNull(head, "pdfdata.head");
		head.validate();
		ValidationException.throwIfNull(body, "pdfdata.body");
		body.validate();
	}
}
