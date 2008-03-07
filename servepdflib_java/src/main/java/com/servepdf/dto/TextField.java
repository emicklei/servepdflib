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

import com.thoughtworks.xstream.XStream;

public class TextField {
	public String name;
	public String value;
	
	public TextField(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	public void validate() throws ValidationException {
		ValidationException.throwUnlessNonEmpty(name, "pdfdata.body.field[?].name");
		ValidationException.throwIfNull(value, "pdfdata.body.field[?].value");
	}
	public static void setup(XStream xstream) {
		xstream.alias("field", TextField.class);
		xstream.useAttributeFor("name", String.class);
		xstream.useAttributeFor("value", String.class);
		xstream.useAttributeFor("version", String.class);
	}
}
