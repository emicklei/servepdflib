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


public class Head {
	public String documentURL;
	public String accessKey;
	public String applicationKey;
	public String cacheControl = "no-cache";
	public String unserviceableURL;
	private Mail mail = null; // optional	
	
	public static String CACHE_NO = "no-cache";
	public static String CACHE_ASSETS= "cache-assets"; // template,images,...
	public static String CACHE_SESSION = "cache-for-session";
		
	public Mail getMail(){
		if (mail == null) mail = new Mail();
		return mail;
	}
	
	public void validate() throws ValidationException {
		ValidationException.throwUnlessURL(documentURL,"documentURL");
		ValidationException.throwUnlessNonEmpty(accessKey,"accessKey");
		ValidationException.throwUnlessNonEmpty(cacheControl,"cacheControl");
		ValidationException.throwUnlessOneOf(cacheControl, "pdfdata.head.cacheControl", new String[]{CACHE_NO,CACHE_ASSETS,CACHE_SESSION});
		if (mail != null) mail.validate();
	}
}
