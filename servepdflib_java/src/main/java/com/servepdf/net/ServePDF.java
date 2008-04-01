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
package com.servepdf.net;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.servepdf.dto.PDFData;

public class ServePDF {
	
	public String base_url = "http://pws.servepdf.com";
	private HttpClient http  = new HttpClient();
	
	public void process(PDFData data, OutputStream out, String secretAccessKey){
		PostMethod post = new PostMethod(base_url + "/process");		
		try {
			String authentication = this.computeSignature(data,secretAccessKey);
			post.setRequestEntity(new StringRequestEntity(data.toXML(),"text/xml" , "utf8"));			
			// flex clients no longer can send the Authorization Http Header
			// so a custom header need to be used.
			post.setRequestHeader("pws-authentication", authentication);
			int code = this.http.executeMethod(post);
			if (code != HttpStatus.SC_OK) 
				throw new RuntimeException("post failed returning http code:" + code);
			InputStream pdfStream =  post.getResponseBodyAsStream();
			byte[] buffer = new byte[1024];
			int howMany = 0;
			do {
				howMany = pdfStream.read(buffer);
				if (howMany > 0) out.write(buffer, 0, howMany);
			} while (howMany != -1);
		} catch (Exception ex) {
			throw new RuntimeException("Unable to process PDFData",ex);
		} finally {
			post.releaseConnection();
		}		
	}
	
	private String computeSignature(PDFData data,String secretAccessKey) {
		// TOOD
		return secretAccessKey;
	}
}
