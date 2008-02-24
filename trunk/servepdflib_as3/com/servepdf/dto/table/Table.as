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
package com.servepdf.dto.table
{
	import com.servepdf.dto.PDFData;
	import com.servepdf.dto.TextField;
	
	public class Table
	{
		public var rows:Array = [];
		
		public function Table(rows:int=0,columns:int=0)
			{
			super();
			// initialize with rows and cells
			for(var r:int=0;r<rows;r++) 
				this.rows.push(new Row(columns));
		}
		/**
		 * Return the first row with has a cell at a given column with a given contents
		 */
		public function findLastRowWithCell(column:int,contents:String):Row {
			var found:Row = null
			for(var r:int=0;r<rows.length;r++) {
				var eachRow:Row = rows[r]
				var first:Cell = eachRow.cells[column]
				if (contents == first.contents) {
					found = eachRow
				}
			}
			return found
		}
		public function addAsTextFieldsTo(fieldNamePrefixes:Array,data:PDFData):void {
			for(var r:int=0;r<rows.length;r++) {
				var eachRow:Row = rows[r]
				for(var c:int=0;c<eachRow.cells.length;c++) {
					var eachCell:Cell = eachRow.cells[c]
					var name:String = fieldNamePrefixes[c]
					name = name + "["
					name = name + String(r)
					name = name + "]"
					var field:TextField = new TextField(name,eachCell.contents)
					data.body.fields.push(field)
				}
			}			
		}
		public function toXML():XML {
			var table:XML = <table/>
			for(var r:int=0;r<rows.length;r++) {
				var eachRow:Row = rows[r]
				table.appendChild(eachRow.toXML())
			}			
			return table;
		}
	}
}