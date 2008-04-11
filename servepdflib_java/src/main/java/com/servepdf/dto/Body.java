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
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class Body {
	public List<Content> contentList = new ArrayList<Content>();

	public Body(){
	    this.contentList.add(new Content());
	}
	
    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }
    
    public void validate() throws ValidationException {
        for (Content each : contentList) {
            each.validate();
        }
    }
    /**
     * Single content API
     * @param name
     * @param text
     * @deprecated
     */
    public void addText(String name, String text) {
        getContent().addText(name, text);        
    }
    /**
     * Single content API
     * @deprecated
     */
    public List<TextField> getFields() {
        return getContent().getFields();
    }

    public static void setup(XStream xstream) {
    	xstream.addImplicitCollection(Body.class, "contentList");	
    }
    public Content getContent(){
        return contentList.get(0);
    }
    
}
