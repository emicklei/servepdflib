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
package com.servepdf.dto
{
	import com.servepdf.net.ServePDF;
	
	[Bindable]
	public class PDFData
	{
		public var head:Head = new Head();
		public var body:Body = new Body();	
		
		public function setField(fieldName:String,fieldValue:String):void{
			body.fields.push(new TextField(fieldName,fieldValue));
		}				
		public function toXML():XML {
			var pdfdata:XML = <pdfdata/>
			pdfdata.appendChild(head.toXML())
			pdfdata.appendChild(body.toXML())
			return pdfdata
		}
		/**
		 * Post the data to ServePDF.com
		 */
		public function send():void {
			new ServePDF().process(this)
		}
	}
}