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
package com.servepdf.net
{
	import flash.net.URLRequest;
	import flash.net.URLRequestMethod;
	import flash.net.navigateToURL;
	import com.servepdf.dto.PDFData;
	
	public class ServePDF
	{		
		public var base_url:String = "http://pws.servepdf.com";		
		/**
		 * Tells what kind of window is opened with the result.
		 * Other values are _self, _parent and _top.
		 * See http://livedocs.adobe.com/flex/2/docs/wwhelp/wwhimpl/js/html/wwhelp.htm?href=00001010.html 
		 */
		public var targetWindow:String = "_blank"; // _self, _parent and _top 
		/**
		 * Process the PDF form with this data. Open the result in a window.
		 */
		public function process(data:PDFData):void {
			this.checkPreconditions(data)
			// compose request and navigate to it
			var request:URLRequest = new URLRequest(base_url+"/process")
       	    request.method = URLRequestMethod.POST
       	    request.contentType = "text/xml"   
       	    var documentURL:String = data.head.documentURL
       	    request.data = data.toXML().toXMLString()
       	    navigateToURL(request,targetWindow);
		}
		/**
		 * Validate before POSTing the data
		 */ 
		private function checkPreconditions(data:PDFData):void {
			// check url
			if (base_url == null || base_url.length == 0) 
				throw new ArgumentError("base url is not valid")
			// check targetWindow
			if (targetWindow == null || targetWindow.length == 0) 
				throw new ArgumentError("targetWindow is not valid")				
			// check data
			if (data == null)
				throw new ArgumentError("data is null and should be PDFData")
		}
	}
}