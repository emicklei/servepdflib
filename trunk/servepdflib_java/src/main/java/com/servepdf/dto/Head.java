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

import com.thoughtworks.xstream.XStream;


public class Head {
    public String documentName;
	public String accessKey;
	public String applicationKey;
	public String cacheControl = "no-cache";
	public String unserviceableURL;
	private Mail mail = null; // optional	
	
	public static final String CACHE_NO = "no-cache";
	public static final String CACHE_ASSETS= "cache-assets"; // template,images,...
	public static final String CACHE_SESSION = "cache-for-session";
		
	public Mail getMail(){
		if (mail == null) mail = new Mail();
		return mail;
	}
	
	public void validate() throws ValidationException {
		ValidationException.throwUnlessNonEmpty(accessKey,"accessKey");
		if (cacheControl != null)
		    ValidationException.throwUnlessOneOf(cacheControl, "pdfdata.head.cacheControl", new String[]{CACHE_NO,CACHE_ASSETS,CACHE_SESSION});
		if (mail != null) mail.validate();
	}
	
	public static void setup(XStream xstream) {
	    xstream.aliasField("document-name", Head.class, "documentName");	       
	    xstream.aliasField("access-key", Head.class, "accessKey");
        xstream.aliasField("application-key", Head.class, "applicationKey");
        xstream.aliasField("cache-control", Head.class, "cacheControl");
        xstream.aliasField("unserviceable-url", Head.class, "unserviceableURL");		
	}
}
