/*
       Copyright 2008 Ernest Micklei, PhilemonWorks.com

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
package com.servepdf.dto
{
	import com.servepdf.dto.table.Table;
	
	public class Content
	{
		public var formURL:String;
		public var fields:Array = new Array();
		public var tables:Array = new Array();	
		
		public function toXML():XML {
			var contentNode:XML = <content>
				<form-url>{formURL}</form-url>
				</content>
			var fieldsNode:XML = <fields/>
			for (var i:int;i<fields.length;i++) {
				var eachField:TextField = TextField(fields[i])
				fieldsNode.appendChild(<field name={eachField.name} value={eachField.value} />)
			}
			contentNode.appendChild(fieldsNode)
			var tablesNode:XML = <tables/>
			for (var j:int;j<tables.length;j++) {
				var eachTable:Table = Table(tables[j])
				tablesNode.appendChild(eachTable.toXML())
			}
			contentNode.appendChild(tablesNode)			
			return contentNode
		}	
		public function validate():void {
			if (formURL == null || formURL.length == 0)
				throw new ArgumentError("content has invalid formURL")
			for (var i:int;i<fields.length;i++) {
				var eachField:TextField = TextField(fields[i])
				eachField.validate()
			}		
			for (var j:int;j<tables.length;j++) {
				var eachTable:Table = Table(tables[j])
				eachTable.validate()
			}					
		}	
		public function addText(fieldName:String,fieldValue:String):void {
			fields.push(new TextField(fieldName,fieldValue));		
		}			
	}
}