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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.servepdf.dto.PDFData;

public class ServePDF {
	public static int RESPONSE_BUFFER_LENGTH = 10240; // 10Kb
	public static String UNSERVICEABLE_URL = "";
	
	public String base_url = "http://pws.servepdf.com";
	private HttpClient http  = new HttpClient();
	
	public void feed(String feedUrl, String formUrl, int maxEntries, OutputStream out, String secretAccessKey) {
		if (feedUrl == null || feedUrl.length() == 0)
			throw new RuntimeException("Missing feedUrl parameter value");
		if (formUrl == null || formUrl.length() == 0)
			throw new RuntimeException("Missing formUrl parameter value");
		if (secretAccessKey == null || secretAccessKey.length() == 0)
			throw new RuntimeException("Missing secretAccessKey parameter value");		
		PostMethod post = new PostMethod(base_url + "/rss");
		post.addParameter("formURL", formUrl);
		post.addParameter("feedURL", feedUrl);
		post.addParameter("maxEntries", String.valueOf(Math.min(maxEntries,1)));
		post.addParameter("unserviceableURL",UNSERVICEABLE_URL);
		try {
			String authentication = this.computeSignature(null,secretAccessKey);
			receiveAfterPost(out, post, authentication);
		} catch (Exception ex) {
			throw new RuntimeException("Unable to process RSS feed",ex);
		} finally {
			post.releaseConnection();
		}
	}
	
	public void process(PDFData data, OutputStream out, String secretAccessKey){
		PostMethod post = new PostMethod(base_url + "/process");		
		try {
			String authentication = this.computeSignature(data,secretAccessKey);
			post.setRequestEntity(new StringRequestEntity(data.toXML(),"text/xml" , "utf8"));			
			receiveAfterPost(out, post, authentication);
		} catch (Exception ex) {
			throw new RuntimeException("Unable to process PDFData",ex);
		} finally {
			post.releaseConnection();
		}		
	}

	private void receiveAfterPost(OutputStream out, PostMethod post, String authentication) throws IOException, HttpException {
		// flex clients no longer can send the Authorization Http Header
		// so a custom header need to be used.
		post.setRequestHeader("pws-authentication", authentication);
		int code = this.http.executeMethod(post);
		if (code != HttpStatus.SC_OK) 
			throw new RuntimeException("post failed returning http code:" + code);
		InputStream pdfStream =  post.getResponseBodyAsStream();
		byte[] buffer = new byte[RESPONSE_BUFFER_LENGTH];
		int howMany = 0;
		do {
			howMany = pdfStream.read(buffer);
			if (howMany > 0) out.write(buffer, 0, howMany);
		} while (howMany != -1);
	}
	
	private String computeSignature(PDFData data,String secretAccessKey) {
		// TODO
		return secretAccessKey;
	}
}
