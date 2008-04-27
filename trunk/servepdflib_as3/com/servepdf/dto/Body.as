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
	import com.servepdf.dto.table.Table;

	public class Body
	{
		public var contentList:Array = new Array();
		
		public function getContent():Content{
			if (contentList.length == 0) {
				contentList.push(new Content());
			}
			return contentList[0]
		}
		
		public function toXML():XML {
			var bodyNode:XML = <body/>
			for (var i:int;i<contentList.length;i++) {
				var eachContent:Content = Content(contentList[i])
				bodyNode.appendChild(eachContent.toXML())
			}		
			return bodyNode
		}	
		public function validate():void {
			for (var i:int;i<contentList.length;i++) {
				var eachContent:Content = Content(contentList[i])
				eachContent.validate()
			}
		}		
	}
}