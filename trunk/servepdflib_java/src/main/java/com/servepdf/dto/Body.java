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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Body {
	private List fields;
	
	public List getFields(){
		if (fields == null) fields = new ArrayList();
		return fields;
	}
	public void validate() throws ValidationException {
		for (Iterator iter = this.getFields().iterator(); iter.hasNext();) {
			TextField element = (TextField) iter.next();
			element.validate();
		}
	}
}
