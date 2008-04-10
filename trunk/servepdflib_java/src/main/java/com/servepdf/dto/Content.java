/*
       Copyright 2008 PhilemonWorks.com

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

import com.servepdf.dto.table.Table;
import com.thoughtworks.xstream.XStream;

/**
 * Content specifies data that is applied to a template document and may result in one or more PDF pages.
 * 
 * @author ernestmicklei
 *
 */
public class Content {
    public String template;
    private List<TextField> fields;
    private List<Table> tables;
    
    public List<TextField> getFields(){
        if (fields == null) fields = new ArrayList<TextField>();
        return fields;
    }
    public List<Table> getTables(){
        if (tables == null) tables = new ArrayList<Table>();
        return tables;
    }   
    public void validate() throws ValidationException {
        ValidationException.throwUnlessURL(template,"template");        
        if (fields == null) return;
        for (TextField field: this.getFields()) {
            field.validate();
        }
        if (tables == null) return;
        for (Table table: this.getTables()) {
            table.validate();
        }
    }
    /**
     * Convencience methods to add a new TextField.
     * @param name
     * @param text
     */
    public void addText(String name, String text) {
        if (name == null || name.length() == 0) return;
        this.getFields().add(new TextField(name,text));
    }
    
    public static void setup(XStream xstream) {
        xstream.alias("content", Content.class);        
        xstream.useAttributeFor("template", String.class);       
    }    
}
