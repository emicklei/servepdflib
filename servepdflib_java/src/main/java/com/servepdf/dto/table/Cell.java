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
package com.servepdf.dto.table;

import com.thoughtworks.xstream.XStream;

public class Cell extends AppearanceHolder {
	public String contents;
	
	public Cell(String contents){
		this.contents = contents;
	}
	
	public Cell(String contents, String font, String fontSize){
        this.contents = contents;
        this.getAppearance().font = font;
        this.getAppearance().fontSize = fontSize;
	}
	
	public String toString(){
		return contents;
	}
		
	public static void setup(XStream xstream) {
		xstream.registerConverter(new CellXMLConvertor());
		xstream.alias("td", Cell.class);
	}
	/**
	 * Find the font specified either from the receiver,its row or its table.
	 * @param row
	 * @param table
	 * @return
	 */
  public String getFont(Row row, Table table) {
      if (hasAppearance()) {
          if (getAppearance().font != null) return getAppearance().font;
      }
      if (row.hasAppearance()) {
          if (row.getAppearance().font != null) return row.getAppearance().font;
      }   
      if (table.hasAppearance()) {
          if (table.getAppearance().font != null) return table.getAppearance().font;
      }
      return null; // no font specified
  }
  /**
   * Find the font size specified either from the receiver,its row or its table.
   * @param row
   * @param table
   * @return
   */
  public String getFontSize(Row row, Table table) {
      if (hasAppearance()) {
          if (getAppearance().fontSize != null) return getAppearance().fontSize;
      }
      if (row.hasAppearance()) {
          if (row.getAppearance().fontSize != null) return row.getAppearance().fontSize;
      }   
      if (table.hasAppearance()) {
          if (table.getAppearance().fontSize != null) return table.getAppearance().fontSize;
      }
      return null; // no fontSize specified
  }  
}
