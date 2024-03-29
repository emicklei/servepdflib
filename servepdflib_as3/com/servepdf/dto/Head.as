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
	public class Head
	{		
		public var accessKey:String;
		public var applicationKey:String;
		public var documentName:String;
		public var unserviceableURL:String;
		public var cacheControl:String = "no-cache";
		private var mail:Mail;	
		
		public function getMail():Mail {
			if (mail == null) mail = new Mail()
			return mail
		}
		
		public function toXML():XML {
			var head:XML = <head>					
					<accessKey>{accessKey}</accessKey>
					<cache-control>{cacheControl}</cache-control>
				</head>
			if (documentName != null) {
				head.appendChild(<document-name>{documentName}</document-name>)
			}	
			if (applicationKey != null) {
				head.appendChild(<application-key>{applicationKey}</application-key>)					
			}	
			if (unserviceableURL != null) {
				head.appendChild(<unserviceable-url>{unserviceableURL}</unserviceable-url>)					
			}								
			if (this.mail != null) {
				head.appendChild(mail.toXML())
			}
			return head
		}	
		public function validate():void {
			if (accessKey == null || accessKey.length == 0)
				throw new ArgumentError("Head has invalid accessKey")
		}		
	}
}