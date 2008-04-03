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

public abstract class AppearanceHolder {
    // captures multiple attributes, if set for a Cell then the non-null values
    // override those from the containing Row or containing Table.
    private CellAppearance appearance = null;


    public CellAppearance getAppearance() {
        if(appearance == null)
            appearance = new CellAppearance();
        return appearance;
    }


    public boolean hasAppearance() {
        return appearance != null;
    }
    
    public void setFont(String font) {
        if (font != null) {
            this.getAppearance().font = font;
        }
    }
    public void setFontSize(String fontSize) {
        if (fontSize != null) {
            this.getAppearance().fontSize = fontSize;
        }
    }    
}
